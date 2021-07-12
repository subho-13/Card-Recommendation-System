from lib.CommonFunctions import *


def available_cards(card_data, df):
    '''Generates Card Scores for Users based on Credit Scores (For New Users)'''

    user_final_list = []
    for i in range(len(df)):
        user_list = []
        max_credit = 0
        credit = df.loc[i, 'credit_score']
        for j, card in enumerate(card_data):
            if (card['Card_Name'] == "College" and df.loc[i, 'job'] == 'student'):
                if (df.loc[i, 'card_type'] != j):
                    user_list = [0.0] * (len(card_data))
                    user_list[j] = 1.0
                    break

            if (card['Card_Name'] != "College" and credit >= card['Credit_Score'] and df.loc[i, 'card_type'] != j):
                if card['Credit_Score'] > max_credit:
                    for i, val in enumerate(user_list):
                        if val > 0 and card_data[i]['Credit_Score'] < (max_credit - 50):
                            user_list[i] = 0
                    user_list.append(1.0)
                    max_credit = card['Credit_Score']
                    continue

                if card['Credit_Score'] >= (max_credit - 50):
                    user_list.append(1.0)
                    max_credit = card['Credit_Score']
                    continue

            user_list.append(0.0)
        user_final_list.append(user_list)
    return user_final_list


def new_user_model(df):
    # =============================================================================
    #     df = pd.read_csv("Updated User Database.csv")
    # =============================================================================
    card_data = card_data_generator()
    card_mapper = card_map_generator(card_data)
    cat_map, indices = category_card_mapper()

    user_final_list = available_cards(card_data, df)
    user_final_list = standardize(user_final_list)
    # np.save('Model_Weights/new_user_final_list', user_final_list)
    user_final_tuple_list = user_tuple_generator(user_final_list, 'New User')
    return user_final_tuple_list
