from sqlalchemy import Column, Integer, Float, ForeignKey, Boolean
from sqlalchemy.orm import declarative_base

from repository.EntityManager import engine

BaseRewardDetails = declarative_base()


class RewardDetails(BaseRewardDetails):
    __tablename__ = "reward_details"

    reward_id = Column(Integer, ForeignKey('card_details.card_id'), primary_key=True)
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
    is_any_reward_given = Column(Boolean, default=False)
    is_reward_given_in_category = Column(Integer, default=0)

    def __repr__(self):
        return "RewardDetails"


BaseRewardDetails.metadata.create_all(engine)
