#!/bin/sh
./init_kafka.sh &
sleep 30
./init_db.sh & 
sleep 2
cd ../DataCollector
gnome-terminal --title 'DataCollector' --tab -- mvn spring-boot:run
sleep 2

cd ../DataAbstraction
gnome-terminal --title 'DataAbstraction' --tab -- mvn spring-boot:run
sleep 2

cd ../FeatureExtractionOne
gnome-terminal --title 'FeatureExtraction' --tab -- mvn spring-boot:run
sleep 2

cd ../OfflineScheduler
gnome-terminal --title 'OfflineScheduler' --tab -- mvn spring-boot:run
sleep 2

cd ../NearlineScheduler
gnome-terminal --title 'NearlineScheduler' --tab -- mvn spring-boot:run
sleep 2

cd ../RecommendationCompiler
gnome-terminal --title 'RecommendationCompiler' --tab -- mvn spring-boot:run
sleep 2

cd ../RecommendationProvider
gnome-terminal --title 'RecommendationProvider' --tab -- mvn spring-boot:run
sleep 2

cd ../RedeemRewardPoints/
export PYTHONPATH=${PWD}
gnome-terminal --title 'RedeemRewardPoints' --tab -- python3 ./Controller.py
sleep 2

cd ../RewardSuggestion/
export PYTHONPATH=${PWD}
gnome-terminal --title 'RewardSuggestion' --tab -- python3 ./Controller.py
sleep 2

cd ../OfflineUnsupervisedRecommendationGenerator
export PYTHONPATH=${PWD}
gnome-terminal --title 'NewUserModel' --tab -- python3 ./driver/NewUserModel.py
sleep 2
gnome-terminal --title 'RuleLearning' --tab -- python3 ./driver/RuleLearning.py
sleep 2
gnome-terminal --title 'AssociationRuleLearning' --tab -- python3 ./driver/AssociationRuleLearning.py
sleep 2
gnome-terminal --title 'SelfOrganizingMaps' --tab -- python3 ./driver/SelfOrganizingMaps.py
sleep 2
gnome-terminal --title 'KpcaSimilarity' --tab -- python3 ./driver/KpcaSimilarity.py
sleep 2
gnome-terminal --title 'NeuralEmbedding' --tab -- python3 ./driver/NeuralEmbedding.py
sleep 2

cd ../NearlineSupervisedRecommendationTrainer/
export PYTHONPATH=${PWD}
gnome-terminal --title 'XGBoostTrainer' --tab -- python3 ./driver/XgboostSupervised.py
sleep 2

cd ../NearlineSupervisedRecommendationGenerator/
export PYTHONPATH=${PWD}
gnome-terminal --title 'XGBoostGenerator' --tab -- python3 ./driver/XgboostSupervised.py
sleep 2

cd ../recommendation-ui
gnome-terminal --title 'UI' --tab -- serve -s build
sleep 2

cd ../
gnome-terminal --title 'Driver' --tab -- python3 ./Driver.py
