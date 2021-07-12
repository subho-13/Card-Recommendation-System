package com.wf.recommendationcompiler.service;

import com.wf.contractlib.contracts.GeneratedRecommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class GeneratedRecommendationConsumer {
    private DatabaseHandler databaseHandler;

    @Autowired
    public void setDatabaseHandler(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    @KafkaListener(groupId = "RecommendationCompiler", topics = "GeneratedRecommendation",
            containerFactory = "generatedRecommendationListenerFactory")
    public void consumeGeneratedRecommendation(GeneratedRecommendation generatedRecommendation) {
        databaseHandler.handle(generatedRecommendation);
    }
}
