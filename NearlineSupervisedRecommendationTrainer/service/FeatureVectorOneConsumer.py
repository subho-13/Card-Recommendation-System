import json
from threading import Thread

from kafka import KafkaConsumer
from kafka.coordinator.assignors.roundrobin import RoundRobinPartitionAssignor

from Configuration import consumer_gid, bootstrap_servers
from entities.UserDetails import UserDetails
from lib.CommonDicts import card_dict
from repository.DatabaseHandler import save_user_details_from_feature_vector_one


def convert_to_user_details(message):
    user_details = UserDetails()
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
