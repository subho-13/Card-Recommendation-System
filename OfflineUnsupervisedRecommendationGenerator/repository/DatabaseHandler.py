from entities.FeatureVectorOne import FeatureVectorOne
from repository.EntityManager import session, engine
import pandas as pd


def save_feature_vector_one(feature_vector_one_dict):
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

    check_if_exists = bool(session.query(FeatureVectorOne).filter_by(User_Id=feature_vector_one_dict['User_Id']).first())

    if check_if_exists:
        session.query(FeatureVectorOne).filter_by(User_Id=feature_vector_one_dict['User_Id']).delete()

    session.add(feature_vector_one)
    session.commit()

def load_feature_vector_one_df():
    return pd.read_sql_table('feature_vector_one', engine)