from sqlalchemy import Column, Integer
from sqlalchemy.orm import declarative_base, relationship

from repository.EntityManager import engine

BaseCustomerDetails = declarative_base()


class CustomerDetails(BaseCustomerDetails):
    __tablename__ = "customer_details"

    customer_id = Column(Integer, nullable=False)
    card_id = Column(Integer, primary_key=True)
    # expenditure = relationship('ExpenditureDetails', uselist=False, backref='customer_details')
    # reward = relationship('RewardDetails', uselist=False, backref='customer_details')

    def __repr__(self):
        return "CustomerDetails"


BaseCustomerDetails.metadata.create_all(engine)
