from sqlalchemy import Column, Integer, String, Boolean, Float, Date
from sqlalchemy import create_engine
from sqlalchemy.orm import declarative_base
from sqlalchemy.orm import sessionmaker

from Configuration import postgres_db_uri

engine = create_engine(postgres_db_uri, echo=False)

Base = declarative_base()
class UserDetails(Base):
    __tablename__ = "user_details"

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
    best_card = Column(Integer)

    def __repr__(self):
        return "UserDetails"    


Session = sessionmaker(bind=engine)
Session.configure(bind=engine)
session = Session()
Base.metadata.create_all(engine)