import threading
from time import sleep

from readerwriterlock import rwlock

from service.CompiledRecommendationConsumer import CompiledRecommendationConsumer
from service.FeatureVectorOneConsumer import FeatureVectorOneConsumer
from service.SupervisedModelProducer import SupervisedModelProducer


def main(topic, supervised_model):
    event = threading.Event()
    event.set()
    rwlock_writer = rwlock.RWLockWriteD()

    compiled_recommendation_consumer = CompiledRecommendationConsumer(event, rwlock_writer)
    feature_vector_one_consumer = FeatureVectorOneConsumer(event, rwlock_writer)
    supervised_model_producer = SupervisedModelProducer(event, rwlock_writer, topic, supervised_model)

    threads = [
        compiled_recommendation_consumer,
        feature_vector_one_consumer,
        supervised_model_producer
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
