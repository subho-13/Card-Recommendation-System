from sqlalchemy.orm import sessionmaker

from entities.FeatureVectorOne import FeatureVectorOne
from repository.EntityManager import engine


def get_session():
    Session = sessionmaker(bind=engine)
    Session.configure(bind=engine)
    session = Session()
    return session


def get_feature_vector_one(User_Id):
    session = get_session()
    try :
        feature_vector_one = session.query(FeatureVectorOne).filter_by(User_Id=User_Id).first()
        session.expunge_all()
    except:
        feature_vector_one = None
    finally:
        session.close()
    return feature_vector_one


def convert_dict_to_feature_vector_one(feature_vector_one_dict):
    feature_vector_one = FeatureVectorOne(User_Id=feature_vector_one_dict['User_Id'],
                                          new_user=feature_vector_one_dict['new_user'],
                                          credit_score=feature_vector_one_dict['credit_score'],
                                          card_issue_date=feature_vector_one_dict['card_issue_date'],
                                          card_type=feature_vector_one_dict['card_type'],
                                          job=feature_vector_one_dict['job'],
                                          Education=feature_vector_one_dict['Education'],
                                          Entertainment=feature_vector_one_dict['Entertainment'],
                                          Food=feature_vector_one_dict['Food'],
                                          Gas_trans=feature_vector_one_dict['Gas_trans'],
                                          Grocery_net=feature_vector_one_dict['Grocery_net'],
                                          Grocery_pos=feature_vector_one_dict['Grocery_pos'],
                                          Health=feature_vector_one_dict['Health'],
                                          Home=feature_vector_one_dict['Home'],
                                          Hotel=feature_vector_one_dict['Hotel'],
                                          Kids_pets=feature_vector_one_dict['Kids_pets'],
                                          Misc_net=feature_vector_one_dict['Misc_net'],
                                          Misc_pos=feature_vector_one_dict['Misc_pos'],
                                          Personal=feature_vector_one_dict['Personal'],
                                          Shop_net=feature_vector_one_dict['Shop_net'],
                                          Shop_pos=feature_vector_one_dict['Shop_pos'],
                                          Travel=feature_vector_one_dict['Travel']
                                          )
    return feature_vector_one


def save_feature_vector_one(feature_vector_one_dict):
    feature_vector_one = convert_dict_to_feature_vector_one(feature_vector_one_dict)
    session = get_session()
    try:
        session.merge(feature_vector_one)
        session.commit()
    except:
        session.rollback()
    finally:
        session.close()
