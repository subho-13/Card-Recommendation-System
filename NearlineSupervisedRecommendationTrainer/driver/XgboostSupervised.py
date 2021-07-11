from models.XgboostSupervised import XGBoostObject
from driver.main import main

xgboost_object = XGBoostObject()

if __name__ == "__main__":
    main("XGBoost Supervised", xgboost_object)

