from entities.UserDetails import UserDetails
from repository.EntityManager import session

def save_user_details_from_compiled_recommendations(user_details):
    user_details_db = session.query(UserDetails).filter_by(User_Id=message['User_Id']).first()

    if user_details_db is not None:
        user_details_db.best_card = user_details.best_card        
    else:        
        session.add(user_details)
            
    session.commit()     


def save_user_details_from_feature_vector_one(user_details):
    user_details = UserDetails(User_Id=message['User_Id'],
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
                                          Travel=message['Travel'],
                                          best_card = 0
                                          )

    user_details_db = session.query(UserDetails).filter_by(User_Id=message['User_Id']).first()

    if user_details_db is not None:
        user_details.best_card = user_details_db.best_card        
        session.query(UserDetails).filter_by(User_Id=message['User_Id']).delete()

    session.add(user_details)
    session.commit()    


def load_dataframe():
    # Drop nan rows
    return  # df = pd.read_sql
