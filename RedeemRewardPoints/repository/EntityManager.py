from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

from Configuration import postgres_db_uri

engine = create_engine(postgres_db_uri, pool_size=2, max_overflow=0, connect_args={'connect_timeout': 70}, echo=False)
Session = sessionmaker(bind=engine, expire_on_commit=False)
Session.configure(bind=engine)
session = Session()
