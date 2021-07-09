from sqlalchemy import Column, Integer, Float, ForeignKey
from sqlalchemy.orm import declarative_base

from repository.EntityManager import engine

BaseExpenditureDetails = declarative_base()


class ExpenditureDetails(BaseExpenditureDetails):
    __tablename__ = "expenditure_details"

    expenditure_id = Column(Integer, ForeignKey('card_details.card_id'), PrimaryKey=True)
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
        return "ExpenditureDetails"


BaseExpenditureDetails.metadata.create_all(engine)
