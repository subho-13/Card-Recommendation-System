import json
from threading import Thread

from kafka import KafkaProducer

from Configuration import bootstrap_servers
from lib.CommonDicts import card_dict


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


class SupervisedModelProducer(Thread):
    def __init__(self, topic, event, rwlock, generate_model):
        super(SupervisedModelProducer, self).__init__()
        self.kafka_producer = KafkaProducer(
            bootstrap_servers=bootstrap_servers,
            value_serializer=lambda message: json.dumps(message).encode('utf-8')
        )

        self.topic = topic
        self.event = event
        self.rwlock = rwlock
        self.generate_model = generate_model

    def run(self):
        while self.event.is_set():
            with self.rwlock.gen_wlock():
                supervised_model = self.generate_model()
                self.kafka_producer.send(self.topic, supervised_model)
