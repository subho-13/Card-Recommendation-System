import json
from datetime import datetime
from threading import Thread

from kafka import KafkaConsumer
from kafka.coordinator.assignors.roundrobin import RoundRobinPartitionAssignor

from Configuration import consumer_gid, bootstrap_servers
from entities.UserDetails import UserDetails
from lib.CommonDicts import card_dict, purchase_category_dict, job_dict
from repository.DatabaseHandler import save_user_details_from_feature_vector_one


def get_card_issue_date(card_issue_unix_time):
    time = datetime.fromtimestamp(card_issue_unix_time)
    return time.strftime('%Y-%m-%d')


def convert_to_user_details(message):
    user_dict = {
        'User_Id': message['customerID'],
        'job': job_dict[message['job']],
        'new_user': message['newUser'],
        'card_issue_date': get_card_issue_date(message['cardIssueUnixTime']),
        'card_type': card_dict[message['cardType']],
        'credit_score': message['creditScore']
    }

    for category in purchase_category_dict:
        if category in message['purchaseExpenditureMap']:
            user_dict[purchase_category_dict[category]] = float(message['purchaseExpenditureMap'][category])
        else:
            user_dict[purchase_category_dict[category]] = 0.0

    user_details = UserDetails(User_Id=user_dict['User_Id'],
                               new_user=user_dict['new_user'],
                               credit_score=user_dict['credit_score'],
                               card_issue_date=user_dict['card_issue_date'],
                               card_type=user_dict['card_type'],
                               job=user_dict['job'],
                               Education=user_dict['Education'],
                               Entertainment=user_dict['Entertainment'],
                               Food=user_dict['Food'],
                               Gas_trans=user_dict['Gas_trans'],
                               Grocery_net=user_dict['Grocery_net'],
                               Grocery_pos=user_dict['Grocery_pos'],
                               Health=user_dict['Health'],
                               Home=user_dict['Home'],
                               Hotel=user_dict['Hotel'],
                               Kids_pets=user_dict['Kids_pets'],
                               Misc_net=user_dict['Misc_net'],
                               Misc_pos=user_dict['Misc_pos'],
                               Personal=user_dict['Personal'],
                               Shop_net=user_dict['Shop_net'],
                               Shop_pos=user_dict['Shop_pos'],
                               Travel=user_dict['Travel'],
                               best_card=0
                               )

    return user_details


class FeatureVectorOneConsumer(Thread):
    def __init__(self, event, rwlock):
        super(FeatureVectorOneConsumer, self).__init__()
        self.kafka_consumer = KafkaConsumer("FeatureVectorOne", group_id=consumer_gid,
                                            bootstrap_servers=bootstrap_servers,
                                            auto_offset_reset='latest',
                                            value_deserializer=lambda message: json.loads(message.decode('utf-8')),
                                            partition_assignment_strategy=[RoundRobinPartitionAssignor]
                                            )

        self.event = event
        self.rwlock = rwlock

    def run(self):
        for message in self.kafka_consumer:
            with self.rwlock.gen_rlock():
                user_details = convert_to_user_details(message.value)
                save_user_details_from_feature_vector_one(user_details)
