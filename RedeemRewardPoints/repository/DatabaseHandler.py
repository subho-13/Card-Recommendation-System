from sqlalchemy.orm import sessionmaker

from entities.CustomerDetails import CustomerDetails
from entities.ExpenditureDetails import ExpenditureDetails
from entities.RewardDetails import RewardDetails
from repository.EntityManager import engine


def get_session():
    session_maker = sessionmaker(bind=engine)
    session_maker.configure(bind=engine)
    session = session_maker()
    return session


def get_expenditure_details(card_id):
    session = get_session()
    expenditure_details = session.query(ExpenditureDetails).filter_by(expenditure_id=card_id).first()
    session.expunge_all()
    return expenditure_details


def get_reward_details(card_id):
    session = get_session()
    reward_details = session.query(RewardDetails).filter_by(reward_id=card_id).first()
    session.expunge_all()
    return reward_details


def save_details(details):
    session = get_session()
    try:
        session.merge(details)
        session.commit()
    except:
        session.rollback()


def get_customer_details(customer_id):
    session = get_session()
    customer_details = session.query(CustomerDetails).filter_by(customer_id=customer_id).first()
    session.expunge_all()
    return customer_details
