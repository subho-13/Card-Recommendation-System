import warnings
import numpy as np
from lib.CommonFunctions1 import *
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from xgboost import XGBClassifier
from lib.CommonFunctions1 import *

warnings.filterwarnings('ignore')

def predict_proba_ordered(probs, classes_, all_classes):
    proba_ordered = np.zeros((probs.shape[0], all_classes.size), dtype=np.float)
    sorter = np.argsort(all_classes)
    idx = sorter[np.searchsorted(all_classes, classes_, sorter=sorter)]
    proba_ordered[:, idx] = probs
    return proba_ordered


warnings.filterwarnings('ignore')


def supervised_data_preparation(df):
    '''Removes unwanted columns and splits the data into input matrix X and output vector y'''

    df_clean = df.drop(labels=['job', 'card_issue_date', 'card_type'], axis=1)
    X = df_clean.iloc[:, 1:-1].values
    y = df_clean.iloc[:, -1].values
    return X, y


def eligibility(card_data, user_final_list, credit, job):
    for i in range(0, len(user_final_list)):
        for j in range(0, len(card_data)):
            if card_data[j]['Card_Name'] == 'College' and job != 'student':
                user_final_list[i][j] = 0.0
            if (credit < card_data[j]['Credit_Score']):
                user_final_list[i][j] = 0.0
    return user_final_list


def train_test_data_generator(X, y):
    '''Splits the data in testing and training data and scales it'''

    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2)
    sc = StandardScaler()
    X_train = sc.fit_transform(X_train)
    X_test = sc.transform(X_test)
    X = sc.transform(X)
    return X_train, X_test, y_train, y_test, X, sc


def user_list_generator(user_dict):
    '''Converts the spendings of user from dict form to list form'''
    user_id = user_dict['User_Id']
    cats = ['credit_score', 'Education', 'Entertainment', 'Food', 'Gas_trans', 'Grocery_net',
            'Grocery_pos', 'Health', 'Home', 'Hotel', 'Kids_pets', 'Misc_net',
            'Misc_pos', 'Personal', 'Shop_net', 'Shop_pos', 'Travel']

    user_list = []
    for cat in cats:
        user_list.append(user_dict[cat])
    user_list = np.array(user_list)
    return user_id, user_list


def prediction_generator(model, X_train, X_test, y_train, y_test, X):
    '''Trains the model and returns card probabilities for every user'''

    warnings.filterwarnings('ignore')
    history = model.fit(X_train, y_train)
    y_pred = model.predict_proba(X)
    return y_pred, history, model


def xgb_supervised(df):
    # =============================================================================
    #     df = pd.read_csv('Supervised User Database.csv')
    #     df_1, empty_list = new_user_remover(df)
    # =============================================================================
    X, y = supervised_data_preparation(df)
    X_train, X_test, y_train, y_test, X, sc = train_test_data_generator(X, y)

    model = XGBClassifier(best_score=0.00369, booster='gbtree', gamma=0.7911, max_depth=2, random_state=0)
    user_final_list, history, model = prediction_generator(model, X_train, X_test, y_train, y_test, X)
    return sc, model


class XGBoostObject:
    def __init__(self):
        self.sc, self.model = None, None

    def train(self, df):
        self.sc, self.model = xgb_supervised(df)

    def update(self, new_model):
        self.sc = new_model.sc
        self.model = new_model.model

    def generate_rec(self, user_dict):
        user_id, user_list = user_list_generator(user_dict)
        user_list = np.reshape(user_list, (1, 17))
        user_list = self.sc.transform(user_list)
        card_score = self.model.predict_proba(user_list)
        all_classes = np.arange(0, 9, 1)
        card_score = predict_proba_ordered(card_score, self.model.classes_, all_classes)
        credit_score = user_dict['credit_score']
        card_data = card_data_generator()
        job = user_dict['job']
        job = job.lower()
        card_score = eligibility(card_data, card_score, credit_score, job)
        card_score = standardize(card_score)
        card_score = (np.reshape(card_score, (9,))).tolist()
        return (user_id, 'XGBoost Model', card_score)
