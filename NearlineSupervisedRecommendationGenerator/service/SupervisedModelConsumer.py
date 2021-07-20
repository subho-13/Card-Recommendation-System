import pickle
from threading import Thread

from kafka import KafkaConsumer
from kafka.coordinator.assignors.roundrobin import RoundRobinPartitionAssignor

from Configuration import bootstrap_servers


class SupervisedModelConsumer(Thread):
    def __init__(self, event, rwlock, topic, group_id, supervised_model):
        super(SupervisedModelConsumer, self).__init__()
        self.kafka_consumer = KafkaConsumer(topic, group_id=group_id,
                                            bootstrap_servers=bootstrap_servers,
                                            auto_offset_reset='latest',
                                            value_deserializer=lambda message: pickle.loads(message),
                                            partition_assignment_strategy=[RoundRobinPartitionAssignor]
                                            )

        self.event = event
        self.rwlock = rwlock
        self.supervised_model = supervised_model

    def run(self):
        for message in self.kafka_consumer:
            with self.rwlock.gen_wlock():
                updated_supervised_model = message.value
                self.supervised_model.update(updated_supervised_model)

                if not self.event.is_set():
                    return
