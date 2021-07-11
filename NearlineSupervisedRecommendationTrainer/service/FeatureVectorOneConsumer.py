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

def convert_to_user_dict(message):  
    temp = {
        'User_Id': message['customerID'],
        'job': job_dict[message['job']],
        'new_user': message['newUser'],
        'card_issue_date': get_card_issue_date(message['cardIssueUnixTime']),
        'card_type': card_dict[message['cardType']],
        'credit_score': message['creditScore']
    }

    for category in purchase_category_dict:
        if category in message['purchaseExpenditureMap']:
            temp[purchase_category_dict[category]] = float(message['purchaseExpenditureMap'][category])
        else:
            temp[purchase_category_dict[category]] = 0.0    

    return temp



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
                user_dict = convert_to_user_dict(message.value)
                save_user_details_from_feature_vector_one(user_dict)
