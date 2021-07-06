from driver.main import main
from entities.FeatureVectorOne import engine
from entities.FeatureVectorOneRepository import check_if_user_is_new, write_to_db
from models.PcaKmeans import pca_kmeans
from util.Adapter import generate_feature_vector_one


def toSend(User_Id):
    return not check_if_user_is_new(User_Id)


def model_driver():
    return pca_kmeans("feature_vector_one", engine)


main(model_driver, toSend, "FeatureVectorOne", generate_feature_vector_one, write_to_db)
