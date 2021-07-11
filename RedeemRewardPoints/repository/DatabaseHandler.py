from entities.CardDetails import CardDetails
from entities.ExpenditureDetails import ExpenditureDetails
from entities.RewardDetails import RewardDetails

from repository.EntityManager import session


def get_expenditure_details(card_id):
    expenditure_details = session.query(ExpenditureDetails).filter_by(expenditure_id=card_id).first()
    return expenditure_details


def get_reward_details(card_id):
    reward_details = session.query(RewardDetails).filter_by(reward_id=card_id).first()
    return reward_details


def save_expenditure_details(expenditure_details):
    is_present = bool(
        session.query(ExpenditureDetails).filter_by(expenditure_id=expenditure_details.expenditure_id).first())

    if is_present:
        session.query(ExpenditureDetails).filter_by(expenditure_id=expenditure_details.expenditure_id).delete()

    session.add(expenditure_details)
    session.commit()


def save_reward_details(reward_details):
    is_present = bool(session.query(RewardDetails).filter_by(reward_id=reward_details.reward_id).first())

    if is_present:
        session.query(RewardDetails).filter_by(reward_id=reward_details.reward_id).delete()

    session.add(reward_details)
    session.commit()


def save_card_details(card_details):
    is_present = bool(session.query(CardDetails).filter_by(card_id=card_details.card_id).first())

    if not is_present:
        session.add(card_details)
        session.commit()


def get_card_details(customer_id):
    return session.query(CardDetails).filter_by(customer_id=customer_id)
