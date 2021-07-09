from sqlalchemy import Column, Integer, Float, ForeignKey, Boolean
from sqlalchemy.orm import declarative_base

from repository.EntityManager import engine

BaseRewardDetails = declarative_base()


class RewardDetails(BaseRewardDetails):
    __tablename__ = "reward_details"

    reward_id = Column(Integer, ForeignKey('card_details.card_id'), primary_key=True)
    card_type = Column(Integer)
    Education = Column(Float, default=0)
    Entertainment = Column(Float,default=0)
    Food = Column(Float,default=0)
    Gas_trans = Column(Float,default=0)
    Grocery_net = Column(Float,default=0)
    Grocery_pos = Column(Float,default=0)
    Health = Column(Float,default=0)
    Home = Column(Float,default=0)
    Hotel = Column(Float,default=0)
    Kids_pets = Column(Float,default=0)
    Misc_net = Column(Float,default=0)
    Misc_pos = Column(Float,default=0)
    Personal = Column(Float,default=0)
    Shop_net = Column(Float,default=0)
    Shop_pos = Column(Float,default=0)
    Travel = Column(Float,default=0)
    is_any_reward_given = Column(Boolean, default=False)
    is_reward_given_in_category = Column(Integer, default=0)

    def __repr__(self):
        return "RewardDetails"


BaseRewardDetails.metadata.create_all(engine)
