import json
from threading import Thread

from kafka import KafkaConsumer
from kafka.coordinator.assignors.roundrobin import RoundRobinPartitionAssignor

from Configuration import bootstrap_servers, consumer_gid
from entities.UserDetails import UserDetails
from lib.CommonDicts import card_dict
from repository.DatabaseHandler import save_user_details_from_compiled_recommendations


def convert_to_user_details(message):
    user_details = UserDetails()

    user_details.User_Id = message['customerID']

    max_score = 0
    card_type = 0
    for card in message['cardConfidenceMap']:
        if message['cardConfidenceMap'][card] > max_score:
            max_score = message['cardConfidenceMap'][card]
            card_type = card_dict[card]

    user_details.best_card = card_type

    return user_details


class CompiledRecommendationConsumer(Thread):
    def __init__(self, event, rwlock):
        super(CompiledRecommendationConsumer, self).__init__()
        self.kafka_consumer = KafkaConsumer("CompiledRecommendation", group_id=consumer_gid,
                                            bootstrap_servers=bootstrap_servers,
                                            auto_offset_reset='latest',
                                            value_deserializer=lambda message: json.loads(message.decode('utf-8')),
                                            partition_assignment_strategy=[RoundRobinPartitionAssignor]
                                            )

        self.event = event
        self.rwlock = rwlock

    def run(self):
        for message in self.kafka_consumer:
            with self.rwlock.gen_wlock():
                user_details = convert_to_user_details(message.value)
                save_user_details_from_compiled_recommendations(user_details)

            if not self.event.is_set():
                return
