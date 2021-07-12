import pickle
from threading import Thread
from time import sleep

from kafka import KafkaProducer

from Configuration import bootstrap_servers, init_sleep_time, sleep_time, minimum_df_size
from lib.CommonDicts import card_dict
from repository.DatabaseHandler import load_user_details_df


def generate_card_confidence_map(card_confidence_list):
    card_confidence_map = {}

    for card in card_dict:
        index = card_dict[card]
        card_confidence_map[card] = card_confidence_list[index]
    return card_confidence_map


class GeneratedRecommendation:
    def __init__(self, customer_id, model_name, card_confidence_list):
        self.customer_id = customer_id
        self.model_name = model_name
        self.card_confidence_map = generate_card_confidence_map(card_confidence_list)


def load_and_preprocess_df():
    df = load_user_details_df()
    df = df[df['new_user'] == False]
    df.drop('new_user', axis=1, inplace=True)
    return df


class SupervisedModelProducer(Thread):
    def __init__(self, event, rwlock, topic, supervised_model):
        super(SupervisedModelProducer, self).__init__()
        self.kafka_producer = KafkaProducer(
            bootstrap_servers=bootstrap_servers,
            value_serializer=lambda message: pickle.dumps(message)
        )

        self.topic = topic
        self.event = event
        self.rwlock = rwlock
        self.supervised_model = supervised_model
        self.init_sleep_time = init_sleep_time
        self.sleep_time = sleep_time

    def run(self):
        sleep(self.init_sleep_time)

        while self.event.is_set():
            with self.rwlock.gen_wlock():
                df = load_and_preprocess_df()

                if len(df) < minimum_df_size:
                    continue

                self.supervised_model.train(df)
                self.kafka_producer.send(self.topic, self.supervised_model)
                sleep(self.sleep_time)
