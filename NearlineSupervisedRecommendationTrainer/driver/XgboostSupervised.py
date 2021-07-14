from driver.main import main
from models.XgboostSupervised import XGBoostObject

xgboost_object = XGBoostObject()

if __name__ == "__main__":
    main("XGBoostModel", xgboost_object)
