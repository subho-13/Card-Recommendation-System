package com.wf.recommendationcompiler.service;

import com.wf.contractlib.contracts.GeneratedRecommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class GeneratedRecommendationConsumer {
    private DatabaseHandler databaseHandler;

    @Autowired
    public void setDatabaseHandler(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    @KafkaListener(groupId = "RecommendationCompiler", topics = "GeneratedRecommendation",
            containerFactory = "generatedRecommendationListenerFactory")
    public void consumeGeneratedRecommendation(GeneratedRecommendation generatedRecommendation) {
        System.out.println("CONSUMED" + generatedRecommendation);
        databaseHandler.handle(generatedRecommendation);
    }
}
