from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

from Configuration import postgres_db_uri

engine = create_engine(postgres_db_uri, echo=False)
Session = sessionmaker(bind=engine)
Session.configure(bind=engine)
session = Session()
