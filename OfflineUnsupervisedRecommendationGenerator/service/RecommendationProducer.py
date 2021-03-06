import json
from random import randint
from threading import Thread
from time import sleep

from kafka import KafkaProducer

from Configuration import bootstrap_servers, minimum_df_size
from lib.CommonDicts import card_dict
from repository.DatabaseHandler import load_feature_vector_one_df


def generate_card_confidence_map(card_confidence_list):
    card_confidence_map = {}

    for card in card_dict:
        index = card_dict[card]
        card_confidence_map[card] = card_confidence_list[index]
    return card_confidence_map


class GeneratedRecommendation:
    def __init__(self, customer_id, model_name, card_confidence_list):
        self.customerID = customer_id
        self.modelName = model_name
        self.cardConfidenceMap = generate_card_confidence_map(card_confidence_list)


class RecommendationProducer(Thread):
    def __init__(self, event, mutex, rwlock, model_driver, consider_new_users):
        super(RecommendationProducer, self).__init__()
        self.producer = KafkaProducer(
            bootstrap_servers=bootstrap_servers,
            value_serializer=lambda message: json.dumps(message).encode('utf-8')
        )

        self.event = event
        self.mutex = mutex
        self.rwlock = rwlock
        self.model_driver = model_driver
        self.user_map = {}
        self.consider_new_users = consider_new_users

    def run(self):
        while self.event.is_set():
            self.mutex.acquire()

            if not self.event.is_set():
                return

            self.generate_recommendation()

            if not self.event.is_set():
                return

    def generate_recommendation(self):
        sleep(randint(3, 7))

        with self.rwlock.gen_wlock():
            df = self.load_and_preprocess_df()

            if len(df) < minimum_df_size:
                return

            user_card_confidence_list = self.model_driver(df)
            for user_card_confidence in user_card_confidence_list:
                user_card_confidence = self.replace_index_with_user_id(user_card_confidence)
                generated_recommendation = GeneratedRecommendation(user_card_confidence[0],
                                                                   user_card_confidence[1],
                                                                   user_card_confidence[2])
                print(generated_recommendation.__dict__)
                self.producer.send("GeneratedRecommendation", generated_recommendation.__dict__)

    def load_and_preprocess_df(self):
        df = load_feature_vector_one_df()

        if not self.consider_new_users:
            df = df[df['new_user'] == False]

        df.drop('new_user', axis=1, inplace=True)
        df.reset_index(drop=True, inplace=True)
        df = self.replace_user_id_with_index(df)
        return df

    def replace_user_id_with_index(self, df):
        self.user_map = {}
        for i in df.index:
            self.user_map[i] = df.at[i, 'User_Id']
            df.at[i, 'User_Id'] = i

        return df

    def replace_index_with_user_id(self, user_card_confidence):
        assigned_user_id = user_card_confidence[0]
        user_card_confidence[0] = int(self.user_map[assigned_user_id])
        return user_card_confidence
