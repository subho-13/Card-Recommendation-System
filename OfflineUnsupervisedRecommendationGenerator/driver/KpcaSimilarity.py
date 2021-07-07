from driver.main import main
from entities.FeatureVectorOne import engine
from entities.FeatureVectorOneRepository import check_if_user_is_new, write_to_db
from models.KpcaSimilarity import kpca_similarity
from util.Adapter import generate_feature_vector_one


def to_send(User_Id):
    return not check_if_user_is_new(User_Id)


def model_driver():
    return kpca_similarity("feature_vector_one", engine)


main(model_driver, "KpcaSimilarity", to_send, "FeatureVectorOne", generate_feature_vector_one, write_to_db)
