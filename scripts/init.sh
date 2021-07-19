#!/bin/sh
./init_kafka.sh &
sleep 30
./init_db.sh & 
sleep 5
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
sleep 5
gnome-terminal --tab -- python3 ./driver/RuleLearning.py
sleep 2
gnome-terminal --tab -- python3 ./driver/AssociationRuleLearning.py
sleep 2
gnome-terminal --tab -- python3 ./driver/SelfOrganizingMaps.py
sleep 2
gnome-terminal --tab -- python3 ./driver/KpcaSimilarity.py
sleep 2
gnome-terminal --tab -- python3 ./driver/NeuralEmbedding.py
sleep 2
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
sleep 2
gnome-terminal --tab -- python3 ./Driver.py
