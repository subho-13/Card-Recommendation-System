import pandas as pd

from entities.UserDetails import UserDetails
from repository.EntityManager import session, engine


def save_user_details_from_compiled_recommendations(user_details):
    user_details_db = session.query(UserDetails).filter_by(User_Id=user_details.User_Id).first()

    if user_details_db is not None:
        user_details_db.best_card = user_details.best_card
    else:
        session.add(user_details)

    session.commit()


def save_user_details_from_feature_vector_one(user_details):
    user_details_db = session.query(UserDetails).filter_by(User_Id=user_details.User_Id).first()

    if user_details_db is not None:
        user_details.best_card = user_details_db.best_card
        session.query(UserDetails).filter_by(User_Id=user_details.User_Id).delete()

    session.add(user_details)
    session.commit()


def load_user_details_df():
    return pd.read_sql_table('user_details', engine)
