import threading
from time import sleep

from readerwriterlock import rwlock

from service.FeatureVectorOneConsumer import FeatureVectorOneConsumer
from service.GeneratedRecommendationProducer import GeneratedRecommendationProducer
from service.NearlineTriggerConsumer import NearlineTriggerConsumer
from service.SupervisedModelConsumer import SupervisedModelConsumer


def main(topic, group_id, supervised_model):
    event = threading.Event()
    event.set()
    rwlock_writer = rwlock.RWLockWriteD()

    feature_vector_one_consumer = FeatureVectorOneConsumer(event, rwlock_writer)
    supervised_model_consumer = SupervisedModelConsumer(event, rwlock, topic, group_id, supervised_model)
    generated_recommendation_producer = GeneratedRecommendationProducer(supervised_model)
    nearline_trigger_consumer = NearlineTriggerConsumer(event, rwlock_writer, group_id,
                                                        generated_recommendation_producer)

    threads = [
        supervised_model_consumer,
        feature_vector_one_consumer,
        nearline_trigger_consumer
    ]

    for thread in threads:
        thread.start()

    try:
        while True:
            sleep(10)
    except KeyboardInterrupt:
        event.clear()

        for thread in threads:
            thread.join()
