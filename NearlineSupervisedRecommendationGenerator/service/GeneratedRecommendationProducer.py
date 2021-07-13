import json

from kafka import KafkaProducer

from Configuration import bootstrap_servers
from lib.CommonDicts import card_dict
from repository.DatabaseHandler import get_feature_vector_one


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


class GeneratedRecommendationProducer:
    def __init__(self, supervised_model):
        super(GeneratedRecommendationProducer, self).__init__()
        self.producer = KafkaProducer(
            bootstrap_servers=bootstrap_servers,
            value_serializer=lambda message: json.dumps(message).encode('utf-8')
        )

        self.supervised_model = supervised_model

    def produce(self, customerID):
        feature_vector_one = get_feature_vector_one(customerID)

        if feature_vector_one is None:
            return

        if feature_vector_one.new_user:
            return

        user_card_confidence = self.supervised_model.generate_rec(feature_vector_one.__dict__)

        generated_recommendation = GeneratedRecommendation(user_card_confidence[0],
                                                           user_card_confidence[1],
                                                           user_card_confidence[2])

        print(generated_recommendation.__dict__)
        self.producer.send("GeneratedRecommendation", generated_recommendation.__dict__)
