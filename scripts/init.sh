#!/bin/sh
./init_kafka.sh
./init_db.sh
sleep 30
cd ../DataCollector
gnome-terminal --tab -- mvn spring-boot:run
cd ../DataAbstraction
gnome-terminal --tab -- mvn spring-boot:run
cd ../FeatureExtractionOne
gnome-terminal --tab -- mvn spring-boot:run
cd ../OfflineScheduler
gnome-terminal --tab -- mvn spring-boot:run
cd ../NearlineScheduler
gnome-terminal --tab -- mvn spring-boot:run
cd ../RecommendationCompiler
gnome-terminal --tab -- mvn spring-boot:run
cd ../RecommendationProvider
gnome-terminal --tab -- mvn spring-boot:run
cd ../OfflineUnsupervisedRecommendationGenerator
export PYTHONPATH=${PWD}
gnome-terminal --tab -- python3 ./driver/RuleLearning.py
gnome-terminal --tab -- python3 ./driver/AssociationRuleLearning.py
gnome-terminal --tab -- python3 ./driver/SelfOrganizingMaps.py
gnome-terminal --tab -- python3 ./driver/KpcaSimilarity.py
gnome-terminal --tab -- python3 ./driver/NeuralEmbedding.py
gnome-terminal --tab -- python3 ./driver/NewUserModel.py
cd ../NearlineSupervisedRecommendationTrainer/
export PYTHONPATH=${PWD}
gnome-terminal --tab -- python3 ./driver/XgboostSupervised.py
cd ../NearlineSupervisedRecommendationGenerator/
export PYTHONPATH=${PWD}
gnome-terminal --tab -- python3 ./driver/XgboostSupervised.py
cd ../RedeemRewardPoints/
export PYTHONPATH=${PWD}
gnome-terminal --tab -- python3 ./Controller.py
cd ../RewardSuggestion/
export PYTHONPATH=${PWD}
gnome-terminal --tab -- python3 ./Controller.py
cd ../
gnome-terminal --tab ./Driver.py
