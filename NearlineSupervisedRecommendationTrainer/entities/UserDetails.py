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
    best_card = Column(Integer)

    def __repr__(self):
        return "UserDetails"    


Session = sessionmaker(bind=engine)
Session.configure(bind=engine)
session = Session()
Base.metadata.create_all(engine)