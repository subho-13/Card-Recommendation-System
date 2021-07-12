package com.wf.recommendationprovider.service;

import com.wf.contractlib.contracts.featurevector.FeatureVectorOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FeatureVectorOneConsumer {
    private DatabaseHandler databaseHandler;

    @Autowired
    public void setDatabaseHandler(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    @KafkaListener(groupId = "RecommendationProvider", topics = "FeatureVectorOne",
            containerFactory = "featureVectorOneListenerFactory")
    public void consumeFeatureVectorOne(FeatureVectorOne featureVectorOne) {        
        databaseHandler.handle(featureVectorOne);
    }
}
