# Setup

## Cloning Repository

Git clone the TEAM B1 repository.

```bash
git clone https://github.com/preethamlv/TEAM-B1.git
```

## Installing Dependencies

Install the following packages :

1. Java and Maven

2. Postgresql

3. Pip and Python dependencies

4. Apache Kafka

```bash
sudo apt install default-jdk default-jre maven postgresql python3-pip
pip install Flask SQLAlchemy psycopg2-binary kafka-python 
pip install keras tensorflow yellowbrick xgboost pandas numpy
cd Card-Recommendation-System
mkdir ./binaries
# Download Kafka-2.13 from kafka.apache.org/downloads
# Extract the GZIP file in the 'binaries'
```

## Installing Maven Dependencies

Go to the Java Microservices and maven install them in the following order

```bash
cd ./Contractlib
mvn install
cd ../DataCollector
mvn install
cd ../DataAbstraction
mvn install
cd ../FeatureExtractionOne
mvn install
cd ../OfflineScheduler
mvn install
cd ../NearlineScheduler
mvn install
cd ../RecommendationCompiler
mvn install
cd ../RecommendationProvider
mvn install
```

## Creating Databases

Create the following Postgres Database:

```bash
sudo -u postgres psql
```

```plsql
CREATE USER teamb1 WITH PASSWORD 'themonksofteamb1'
CREATE DATABASE data_abstraction_db WITH OWNER teamb1;
CREATE DATABASE redeem_reward_points_db WITH OWNER teamb1;
CREATE DATABASE feature_extraction_one_db WITH OWNER teamb1;
CREATE DATABASE nearline_scheduler_db WITH OWNER teamb1;
CREATE DATABASE offline_scheduler_db WITH OWNER teamb1;
CREATE DATABASE recommendation_compiler_db WITH OWNER teamb1;
CREATE DATABASE recommendation_provider_db WITH OWNER teamb1;
CREATE DATABASE offline_unsupervised_generator_db WITH OWNER teamb1;
CREATE DATABASE nearline_supervised_generator_db WITH OWNER teamb1;
CREATE DATABASE nearline_supervised_trainer_db WITH OWNER teamb1;
ALTER SYSTEM SET max_connections TO '500';
```

## Starting Microservices

Go to the /scripts folder of Card-Recommdendation-System  and Run the following commands

```bash
# Execute only once
chmod +x ./init*
# Start the Microservices
./init.sh
```

If the script fails to run the Kafka-Server, fix the Kafka Path in init_kafka.sh


