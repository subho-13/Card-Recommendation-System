import tensorflow as tf
from sklearn.model_selection import train_test_split
from tensorflow import keras

from lib.CommonFunctions1 import *


def supervised_data_preparation(df):
    df_clean = df.drop(labels=['job', 'card_issue_date', 'card_type'], axis=1)
    X = df_clean.iloc[:, 1:-1].values
    y = df_clean.iloc[:, -1].values
    return X, y


def train_test_data_generator(X, y):
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2)
    sc = StandardScaler()
    X_train = sc.fit_transform(X_train)
    X_test = sc.transform(X_test)
    X = sc.transform(X)
    return X_train, X_test, y_train, y_test, X, sc


def classifier_model1(X_train, X_test, y_train, y_test):
    model = keras.models.Sequential()
    model.add(
        keras.layers.Dense(name='Dense1', units=64, kernel_initializer='uniform', activation='relu', input_shape=(17,)))
    model.add(keras.layers.Dropout(0.2))
    model.add(keras.layers.Dense(name='Dense2', units=32, kernel_initializer='uniform', activation='relu'))
    model.add(keras.layers.Dropout(0.2))
    model.add(keras.layers.Dense(name='Dense3', units=16, kernel_initializer='uniform', activation='relu'))
    model.add(keras.layers.Dense(name='Dense4', units=12, kernel_initializer='uniform', activation='relu'))
    model.add(keras.layers.Dense(name='Dense5', units=9, kernel_initializer='uniform', activation='softmax'))
    model.compile(optimizer='adam', loss='categorical_crossentropy', metrics=['accuracy'])
    return model


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
    history = model.fit(X_train, y_train, validation_data=(X_test, y_test), batch_size=32, epochs=350,
                        use_multiprocessing=True)
    y_pred = model.predict(X)
    return y_pred, model


def neural_supervised(df):
    # =============================================================================
    #     df_1, empty_list = new_user_remover(df)
    # =============================================================================

    card_data = card_data_generator()

    X, y = supervised_data_preparation(df)
    y = tf.keras.utils.to_categorical(y, num_classes=len(card_data), dtype='float32')
    X_train, X_test, y_train, y_test, X, sc = train_test_data_generator(X, y)
    model = classifier_model1(X_train, X_test, y_train, y_test)
    return sc, model


class ANNObject:
    def __init__(self, sc, model):
        self.sc = sc
        self.model = model

    def train(self, df):
        self.sc, self.model = neural_supervised(df)

    def update(self, new_model):
        self.sc = new_model.sc
        self.model = new_model.model

    def generate_rec(self, user_dict):
        user_id, user_list = user_list_generator(user_dict)
        user_list = np.reshape(user_list, (1, 17))
        user_list = self.sc.transform(user_list)
        card_score = self.model.predict(user_list)
        card_score = standardize(card_score)
        card_score = (np.reshape(card_score, (9,))).tolist()
        return (user_id, 'ANN Model', card_score)
