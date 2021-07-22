#!/bin/sh
./init_kafka.sh &
sleep 30
./init_db.sh & 
sleep 5
cd ../DataCollector
gnome-terminal --title 'DataCollector' --tab -- mvn spring-boot:run
cd ../DataAbstraction
gnome-terminal --title 'DataAbstraction' --tab -- mvn spring-boot:run
cd ../FeatureExtractionOne
gnome-terminal --title 'FeatureExtraction' --tab -- mvn spring-boot:run
cd ../OfflineScheduler
gnome-terminal --title 'OfflineScheduler' --tab -- mvn spring-boot:run
cd ../NearlineScheduler
gnome-terminal --title 'NearlineScheduler' --tab -- mvn spring-boot:run
cd ../RecommendationCompiler
gnome-terminal --title 'RecommendationCompiler' --tab -- mvn spring-boot:run
cd ../RecommendationProvider
gnome-terminal --title 'RecommendationProvider' --tab -- mvn spring-boot:run
cd ../RedeemRewardPoints/
export PYTHONPATH=${PWD}
gnome-terminal --title 'RedeemRewardPoints' --tab -- python3 ./Controller.py
cd ../RewardSuggestion/
export PYTHONPATH=${PWD}
gnome-terminal --title 'RewardSuggestion' --tab -- python3 ./Controller.py

cd ../OfflineUnsupervisedRecommendationGenerator
export PYTHONPATH=${PWD}
sleep 3
gnome-terminal --title 'NewUserModel' --tab -- python3 ./driver/NewUserModel.py
sleep 1
gnome-terminal --title 'RuleLearning' --tab -- python3 ./driver/RuleLearning.py
sleep 1
gnome-terminal --title 'AssociationRuleLearning' --tab -- python3 ./driver/AssociationRuleLearning.py
sleep 1
gnome-terminal --title 'SelfOrganizingMaps' --tab -- python3 ./driver/SelfOrganizingMaps.py
sleep 1
gnome-terminal --title 'KpcaSimilarity' --tab -- python3 ./driver/KpcaSimilarity.py
sleep 1
gnome-terminal --title 'NeuralEmbedding' --tab -- python3 ./driver/NeuralEmbedding.py

cd ../NearlineSupervisedRecommendationTrainer/
export PYTHONPATH=${PWD}
gnome-terminal --title 'XGBoostTrainer' --tab -- python3 ./driver/XgboostSupervised.py

cd ../NearlineSupervisedRecommendationGenerator/
export PYTHONPATH=${PWD}
gnome-terminal --title 'XGBoostGenerator' --tab -- python3 ./driver/XgboostSupervised.py

cd ../recommendation-ui
gnome-terminal --title 'UI' --tab -- serve -s build

cd ../
sleep 7
gnome-terminal --title 'Driver' --tab -- python3 ./Driver.py
