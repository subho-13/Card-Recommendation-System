import threading
from threading import Thread
from time import sleep

from flask import Flask
from flask_cors import CORS, cross_origin
from flask_wtf.csrf import CSRFProtect

from readerwriterlock import rwlock

from repository.DatabaseHandler import *
from service.AbstractedTransactionConsumer import AbstractedTransactionConsumer
from service.RestService import RestService
csrf = CSRFProtect()
app = Flask(__name__)
csrf.init_app(app)
cors = CORS(app)
app.config['CORS_HEADERS'] = 'Content-Type'

purchase_category_min_max_dict = {
    "min": {},
    "max": {}
}

event = threading.Event()
event.set()

rwlock_reader = rwlock.RWLockReadD()
rest_service = RestService(rwlock_reader, purchase_category_min_max_dict)


def construct_reward_suggestions(rewardPoints=0, productSuggestions=[]):
    if productSuggestions is None:
        productSuggestions = []
    reward_suggestions = {
        'rewardPoints': rewardPoints,
        'productSuggestions': productSuggestions
    }

    print(reward_suggestions)

    return reward_suggestions


@app.route('/get/<customer_id>')
@cross_origin()
def hello_world(customer_id):
    customer_details = get_customer_details(customer_id)

    if customer_details is None:
        return construct_reward_suggestions()

    expenditure_details = get_expenditure_details(customer_details.card_id)
    reward_details = get_reward_details(customer_details.card_id)
    suggestions = rest_service.call_reward_redeem_suggestion_service(expenditure_details, reward_details)
    return construct_reward_suggestions(suggestions['rewardPoints'], suggestions['product'])


if __name__ == '__main__':
    list_of_threads = [
        AbstractedTransactionConsumer(event, rwlock_reader, purchase_category_min_max_dict),
        Thread(target=app.run, args=("localhost", 9503))
    ]

    try:
        for thread in list_of_threads:
            thread.start()

        while True:
            sleep(5)
    except KeyboardInterrupt:
        event.clear()

        for thread in list_of_threads:
            thread.join()
