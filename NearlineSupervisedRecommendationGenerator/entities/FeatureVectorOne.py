from sqlalchemy import Column, Integer, Boolean, String, Date, Float
from sqlalchemy.orm import declarative_base

from repository.EntityManager import engine

BaseFeatureVectorOne = declarative_base()


class FeatureVectorOne(BaseFeatureVectorOne):
    __tablename__ = "feature_vector_one"

    User_Id = Column(Integer, primary_key=True)
    new_user = Column(Boolean)
    job = Column(String)
    credit_score = Column(Integer)
    card_issue_date = Column(Date)
    card_type = Column(Integer)
    Education = Column(Float)
    Entertainment = Column(Float)
    Food = Column(Float)
    Gas_trans = Column(Float)
    Grocery_net = Column(Float)
    Grocery_pos = Column(Float)
    Health = Column(Float)
    Home = Column(Float)
    Hotel = Column(Float)
    Kids_pets = Column(Float)
    Misc_net = Column(Float)
    Misc_pos = Column(Float)
    Personal = Column(Float)
    Shop_net = Column(Float)
    Shop_pos = Column(Float)
    Travel = Column(Float)

    def __repr__(self):
        return "FeatureVectorOne"


# BaseFeatureVectorOne.MetaData.drop_all(engine)
BaseFeatureVectorOne.metadata.create_all(engine)
