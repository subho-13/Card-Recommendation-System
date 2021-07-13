import pandas as pd
from sqlalchemy.orm import sessionmaker

from entities.UserDetails import UserDetails
from repository.EntityManager import engine


def get_session():
    Session = sessionmaker(bind=engine)
    Session.configure(bind=engine)
    session = Session()
    return session


def save_user_details_from_compiled_recommendations(user_details):
    session = get_session()
    user_details_db = session.query(UserDetails).filter_by(User_Id=user_details.User_Id).first()

    if user_details_db is not None:
        user_details_db.best_card = user_details.best_card
    else:
        session.add(user_details)

    session.commit()


def save_user_details_from_feature_vector_one(user_details):
    session = get_session()
    try :
        session.merge(user_details)
        session.commit()
    except :
        session.rollback()


def load_user_details_df():
    return pd.read_sql_table('user_details', engine)
