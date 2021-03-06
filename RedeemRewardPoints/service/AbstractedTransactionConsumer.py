import json
from threading import Thread

from kafka import KafkaConsumer
from kafka.coordinator.assignors.roundrobin import RoundRobinPartitionAssignor

from Configuration import bootstrap_servers, consumer_gid
from lib.CommonDict import card_dict, purchase_category_int_dict
from repository.DatabaseHandler import *
from service.RewardPointsCalculator import calculate_reward_points


class AbstractedTransaction:
    def __init__(self, customer_id, card_id, purchase_category, transaction_amount, card_type):
        self.customer_id = customer_id
        self.card_id = card_id
        self.purchase_category = purchase_category
        self.transaction_amount = transaction_amount
        self.card_type = card_type


def get_customer_details(abstracted_transaction):
    customer_details = CustomerDetails()
    customer_details.customer_id = abstracted_transaction.customer_id
    customer_details.card_id = abstracted_transaction.card_id
    return customer_details

def convert_to_abstracted_transaction(message):
    abstracted_transaction = AbstractedTransaction(
        message['customerID'],
        message['cardID'],
        purchase_category_int_dict[message['purchaseCategory']],
        message['transactionAmount'],
        card_dict[message['cardType']]
    )

    return abstracted_transaction


class AbstractedTransactionConsumer(Thread):
    def __init__(self, event, rwlock, purchase_category_min_max_dict):
        super(AbstractedTransactionConsumer, self).__init__()
        self.kafka_consumer = KafkaConsumer("AbstractedTransaction", group_id=consumer_gid,
                                            bootstrap_servers=bootstrap_servers,
                                            auto_offset_reset='latest',
                                            value_deserializer=lambda message: json.loads(message.decode('utf-8')),
                                            partition_assignment_strategy=[RoundRobinPartitionAssignor]
                                            )
        self.event = event
        self.rwlock = rwlock
        self.purchase_category_min_max_dict = purchase_category_min_max_dict

    def run(self):
        for message in self.kafka_consumer:
            abstracted_transaction = convert_to_abstracted_transaction(message.value)
            self.update_purchase_dict(abstracted_transaction)
            reward_details = get_reward_details(abstracted_transaction.card_id)
            expenditure_details = get_expenditure_details(abstracted_transaction.card_id)

            updated_expenditure_details, updated_reward_details = calculate_reward_points(abstracted_transaction,
                                                                                          expenditure_details,
                                                                                          reward_details)

            save_details(updated_reward_details)
            save_details(updated_expenditure_details)
            customer_details = get_customer_details(abstracted_transaction)
            save_details(customer_details)

            if not self.event.is_set():
                return

    def update_purchase_dict(self, abstracted_transaction):
        purchase_category = abstracted_transaction.purchase_category
        transaction_amount = abstracted_transaction.transaction_amount
        with self.rwlock.gen_wlock():
            if purchase_category in self.purchase_category_min_max_dict['min']:
                self.purchase_category_min_max_dict['min'][purchase_category] = min(transaction_amount,
                                                                                    self.purchase_category_min_max_dict[
                                                                                        'min'][
                                                                                        purchase_category])
            else:
                self.purchase_category_min_max_dict['min'][purchase_category] = transaction_amount

            if purchase_category in self.purchase_category_min_max_dict['max']:
                self.purchase_category_min_max_dict['max'][purchase_category] = max(transaction_amount,
                                                                                    self.purchase_category_min_max_dict[
                                                                                        'max'][
                                                                                        purchase_category])
            else:
                self.purchase_category_min_max_dict['max'][purchase_category] = transaction_amount
