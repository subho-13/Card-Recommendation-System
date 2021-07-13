from entities.ExpenditureDetails import ExpenditureDetails
from entities.RewardDetails import RewardDetails
from entities.CustomerDetails import CustomerDetails

from repository.EntityManager import session


def get_expenditure_details(card_id):
    expenditure_details = session.query(ExpenditureDetails).filter_by(expenditure_id=card_id).first()
    return expenditure_details


def get_reward_details(card_id):
    reward_details = session.query(RewardDetails).filter_by(reward_id=card_id).first()
    return reward_details


def save_details(details):
    print(details)
    try:
        session.merge(details)
        session.commit()
    except:
        session.rollback()


def get_customer_details(customer_id):
    return session.query(CustomerDetails).filter_by(customer_id=customer_id)
