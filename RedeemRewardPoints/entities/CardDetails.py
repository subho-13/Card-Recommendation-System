from sqlalchemy import Column, Integer
from sqlalchemy.orm import declarative_base, relationship

from repository.EntityManager import engine

BaseCardDetails = declarative_base()


class CardDetails(BaseCardDetails):
    __tablename__ = "card_details"

    card_id = Column(Integer, primary_key=True)
    customer_id = Column(Integer)
    expenditure = relationship('ExpenditureDetails', uselist=False, backref='card_details')
    reward = relationship('RewardDetails', uselist=False, backref='card_details')

    def __repr__(self):
        return "CardDetails"


BaseCardDetails.metadata.create_all(engine)
