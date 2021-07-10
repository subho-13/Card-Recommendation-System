import json
from threading import Thread
from kafka import KafkaConsumer
from kafka.coordinator.assignors.roundrobin import RoundRobinPartitionAssignor

from Configuration import bootstrap_servers
from Configuration import feature_vector_consumer_gid

class CompiledRecommendationConsumer(Thread):
    def __init__(self, event, rwlock):
        super(CompiledRecommendationConsumer, self).__init__()
        self.kafka_consumer = KafkaConsumer("FeatureVectorOne", group_id=feature_vector_consumer_gid,
                                            bootstrap_servers=bootstrap_servers,
                                            auto_offset_reset='latest',
                                            value_deserializer=lambda message: json.loads(message.decode('utf-8')),
                                            partition_assignment_strategy=[RoundRobinPartitionAssignor]
                                            )

        self.event = event
        self.rwlock = rwlock

    def run(self):
        for message in self.kafka_consumer:
            with 