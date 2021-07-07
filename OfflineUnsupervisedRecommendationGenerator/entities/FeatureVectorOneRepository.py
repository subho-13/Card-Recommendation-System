from FeatureVectorOne import *


def write_to_db(message):
    global session
    feature_vector_one = FeatureVectorOne(User_Id=message['User_Id'],
                                          new_user=message['new_user'],
                                          credit_score=message['credit_score'],
                                          card_issue_date=message['card_issue_date'],
                                          card_type=message['card_type'],
                                          job=message['job'],
                                          Education=message['Education'],
                                          Entertainment=message['Entertainment'],
                                          Food=message['Food'],
                                          Gas_trans=message['Gas_trans'],
                                          Grocery_net=message['Grocery_net'],
                                          Grocery_pos=message['Grocery_pos'],
                                          Health=message['Health'],
                                          Home=message['Home'],
                                          Hotel=message['Hotel'],
                                          Kids_pets=message['Kids_pets'],
                                          Misc_net=message['Misc_net'],
                                          Misc_pos=message['Misc_pos'],
                                          Personal=message['Personal'],
                                          Shop_net=message['Shop_net'],
                                          Shop_pos=message['Shop_pos'],
                                          Travel=message['Travel']
                                          )

    checkIfExists = bool(session.query(FeatureVectorOne).filter_by(User_Id=message['User_Id']).first())

    if checkIfExists:
        session.query(FeatureVectorOne).filter_by(User_Id=message['User_Id']).delete()

    session.add(feature_vector_one)
    session.commit()


def check_if_user_is_new(User_Id):
    global session
    feature_vector_one = session.query(FeatureVectorOne).filter_by(User_Id=User_Id).first()
    return feature_vector_one.isNewUser()
