package com.wf.recommendationprovider.service;

import com.wf.contractlib.contracts.CompiledRecommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CompiledRecommendationConsumer {
    private DatabaseHandler databaseHandler;

    @Autowired
    public void setDatabaseHandler(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    @KafkaListener(groupId = "RecommendationProvider", topics = "CompiledRecommendation",
            containerFactory = "compiledRecommendationListenerFactory")
    public void consumeFeatureVectorOne(CompiledRecommendation compiledRecommendation) {
        System.out.println("Consumed :: " + compiledRecommendation);
        databaseHandler.handle(compiledRecommendation);
    }
}

