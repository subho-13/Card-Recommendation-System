from threading import Thread

from kafka import KafkaConsumer

from Configuration import bootstrap_servers
from Configuration import feature_vector_consumer_gid


class FeatureVectorConsumer(Thread):
    def __init__(self, event, rwlock, topic, convert_to_feature_vector, write_to_db):
        super(FeatureVectorConsumer, self).__init__()
        self.kafka_consumer = KafkaConsumer(topic, group_id=feature_vector_consumer_gid,
                                            bootstrap_servers=bootstrap_servers,
                                            
                                            )
        self.event = event
        self.rwlock = rwlock
        self.convert_to_feature_vector = convert_to_feature_vector
        self.write_to_db = write_to_db

    def run(self):
        for message in self.kafka_consumer:
            with self.rwlock.gen_rlock():
                feature_vector = self.convert_to_feature_vector(message.value)
                print(feature_vector)
                self.write_to_db(feature_vector)
                if not self.event.is_set():
                    return
