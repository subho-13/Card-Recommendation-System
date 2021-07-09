import json

from kafka import KafkaConsumer
from threading import Thread

from kafka.coordinator.assignors.roundrobin import RoundRobinPartitionAssignor

from Configuration import bootstrap_servers, abstracted_transaction_consumer_gid
from repository.DatabaseHandler import *
from RewardPointsCalculator import calculate_reward_points


class AbstractedTransaction:
    def __init__(self, customer_id, card_id, purchase_category, transaction_amount, card_type):
        self.customer_id = customer_id
        self.card_id = card_id
        self.purchase_category = purchase_category
        self.transaction_amount = transaction_amount
        self.card_type = card_type


def get_card_details(abstracted_transaction):
    card_details = CardDetails()
    card_details.customer_id = abstracted_transaction.customer_id
    card_details.card_id = abstracted_transaction.card_id


def convert_to_abstracted_transaction(message):
    abstracted_transaction = AbstractedTransaction()
    abstracted_transaction.customer_id = message['customerID']
    abstracted_transaction.card_id = message['cardID']
    abstracted_transaction.purchase_category = message['purchaseCategory']
    abstracted_transaction.card_type = message['cardType']
    abstracted_transaction.transaction_amount = message['transactionAmount']
    return abstracted_transaction


class FeatureVectorConsumer(Thread):
    def __init__(self, event, convert_to_feature_vector, write_to_db):
        super(FeatureVectorConsumer, self).__init__()
        self.kafka_consumer = KafkaConsumer("AbstractedTransaction", group_id=abstracted_transaction_consumer_gid,
                                            bootstrap_servers=bootstrap_servers,
                                            auto_offset_reset='latest',
                                            value_deserializer=lambda message: json.loads(message.decode('utf-8')),
                                            partition_assignment_strategy=[RoundRobinPartitionAssignor]
                                            )
        self.event = event
        self.write_to_db = write_to_db

    def run(self):
        for message in self.kafka_consumer:
            abstracted_transaction = convert_to_abstracted_transaction(message.value)

            reward_details = get_reward_details(abstracted_transaction.card_id)
            expenditure_details = get_expenditure_details(abstracted_transaction.card_id)

            expenditure_details, reward_details = calculate_reward_points(expenditure_details, reward_details)

            save_reward_details(reward_details)
            save_expenditure_details(expenditure_details)
            save_card_details(get_card_details(abstracted_transaction))

            if not self.event.is_set():
                return
