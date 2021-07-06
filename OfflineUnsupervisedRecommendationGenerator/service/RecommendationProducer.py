import json
from threading import Thread

from kafka import KafkaProducer

from lib.CommonDicts import card_dict

from Configuration import bootstrap_servers


class GeneratedRecommendation:
    def __init__(self, customer_id, model_name, card_confidence_list):
        self.customer_id = customer_id
        self.model_name = model_name
        self.card_confidence_map = self.generate_card_confidence_map(card_confidence_list)

    def generate_card_confidence_map(self, cardConfidenceList):
        card_confidence_map = {}

        for card in card_dict:
            card_confidence_map[card] = cardConfidenceList[card_dict[card]]
        return card_confidence_map


class RecommendationProducer(Thread):
    def __init__(self, event, mutex, rwlock, model_driver, to_send):
        super(RecommendationProducer, self).__init__()
        self.producer = KafkaProducer(
            bootstrap_servers=bootstrap_servers,
            value_serializer=lambda message: json.dumps(message).encode('utf-8')
        )

        self.event = event
        self.mutex = mutex
        self.rwlock = rwlock
        self.model_driver = model_driver
        self.to_send = to_send

    def run(self):
        while self.event.is_set():
            self.mutex.acquire()

            if not self.event.is_set():
                return

            with self.rwlock.gen_wlock():
                user_card_confidence_list = self.model_driver()
                for user_card_confidence in user_card_confidence_list:
                    if self.to_send(user_card_confidence[0]):
                        print(user_card_confidence)
                        generated_recommendation = GeneratedRecommendation(user_card_confidence[0],
                                                                           user_card_confidence[1],
                                                                           user_card_confidence[2])
                        print("Produced :: ", generated_recommendation)
                        self.producer.send("GeneratedRecommendation", generated_recommendation.__dict__)
