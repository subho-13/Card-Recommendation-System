import threading
from time import sleep

from readerwriterlock import rwlock

from service.FeatureVectorOneConsumer import FeatureVectorOneConsumer
from service.RecommendationProducer import RecommendationProducer
from service.TriggerConsumer import TriggerConsumer


def main(model_driver, group_id, consider_new_users):
    event = threading.Event()
    mutex = threading.Lock()
    rwlock_writer = rwlock.RWLockWriteD()

    event.set()
    mutex.acquire()

    trigger_consumer = TriggerConsumer(event, mutex, group_id)
    recommendation_producer = RecommendationProducer(event, mutex, rwlock_writer, model_driver, consider_new_users)
    feature_vector_one_consumer = FeatureVectorOneConsumer(event, rwlock_writer)

    threads = [
        trigger_consumer,
        recommendation_producer,
        feature_vector_one_consumer
    ]

    for thread in threads:
        thread.start()

    try:
        while True:
            sleep(2)
    except KeyboardInterrupt:
        event.clear()

        if mutex.locked():
            mutex.release()

        for thread in threads:
            thread.join()
