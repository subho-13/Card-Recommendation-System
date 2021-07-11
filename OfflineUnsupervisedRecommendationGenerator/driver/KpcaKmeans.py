from driver.main import main
from entities.FeatureVectorOne import engine
from repository.DatabaseHandler import check_if_user_is_new, write_to_db
from models.KpcaKmeans import kpca_kmeans
from util.Adapter import generate_feature_vector_one


def to_send(User_Id):
    return not check_if_user_is_new(User_Id)


def model_driver():
    return kpca_kmeans("feature_vector_one", engine)


main(model_driver, "KpcaKmeans", to_send, "FeatureVectorOne", generate_feature_vector_one, write_to_db)
