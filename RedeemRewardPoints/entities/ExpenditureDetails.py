from sqlalchemy import Column, Integer, Float, ForeignKey
from sqlalchemy.orm import declarative_base
from repository.EntityManager import engine

from entities.CustomerDetails import  CustomerDetails

BaseExpenditureDetails = declarative_base()


class ExpenditureDetails(BaseExpenditureDetails):
    __tablename__ = "expenditure_details"

    expenditure_id = Column(Integer, ForeignKey(CustomerDetails.card_id), primary_key=True)
    card_type = Column(Integer)
    Education = Column(Float, default=0)
    Entertainment = Column(Float, default=0)
    Food = Column(Float, default=0)
    Gas_trans = Column(Float, default=0)
    Grocery_net = Column(Float, default=0)
    Grocery_pos = Column(Float, default=0)
    Health = Column(Float, default=0)
    Home = Column(Float, default=0)
    Hotel = Column(Float, default=0)
    Kids_pets = Column(Float, default=0)
    Misc_net = Column(Float, default=0)
    Misc_pos = Column(Float, default=0)
    Personal = Column(Float, default=0)
    Shop_net = Column(Float, default=0)
    Shop_pos = Column(Float, default=0)
    Travel = Column(Float, default=0)
    
    def __init__(self, id, card_type):
        self.expenditure_id = id
        self.card_type = card_type
        self.Education = 0
        self.Entertainment = 0
        self.Food = 0
        self.Gas_trans = 0
        self.Grocery_net = 0
        self.Grocery_pos = 0
        self.Health = 0
        self.Home = 0
        self.Hotel = 0
        self.Kids_pets = 0
        self.Misc_net = 0
        self.Misc_pos = 0
        self.Personal = 0
        self.Shop_net = 0
        self.Shop_pos = 0
        self.Travel = 0

    def __repr__(self):
        return "ExpenditureDetails"


BaseExpenditureDetails.metadata.create_all(engine)
