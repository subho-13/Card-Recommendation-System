from driver.main import main
from entities.FeatureVectorOne import engine
from repository.DatabaseHandler import check_if_user_is_new, write_to_db
from models.SelfOrganizingMaps import self_organizing_maps
from util.Adapter import generate_feature_vector_one


def to_send(User_Id):
    return not check_if_user_is_new(User_Id)


def model_driver():
    return self_organizing_maps("feature_vector_one", engine)


main(model_driver, "SelfOrganizingMaps", to_send, "FeatureVectorOne", generate_feature_vector_one, write_to_db)
