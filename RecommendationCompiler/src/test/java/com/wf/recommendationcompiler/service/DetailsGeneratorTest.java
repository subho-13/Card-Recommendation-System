package com.wf.recommendationcompiler.service;

import com.wf.contractlib.contracts.GeneratedRecommendation;
import com.wf.contractlib.entities.CardType;
import com.wf.recommendationcompiler.entity.RecommendationDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DetailsGeneratorTest {

    private DetailsGenerator detailsGenerator ;

    @BeforeEach
    void setUp() {
       detailsGenerator =new DetailsGenerator() ;
    }

    @Test
    void detailsGeneratorTest()
    {
        GeneratedRecommendation generatedRecommendation = new GeneratedRecommendation() ;
        RecommendationDetails recommendationDetails = new RecommendationDetails() ;

        Map<CardType,Float> confidenceMap = new HashMap<>() ;
        confidenceMap.put(CardType.CASH_WISE, 0.1f) ;
        confidenceMap.put(CardType.COLLEGE, 0.02f) ;
        confidenceMap.put(CardType.CREDIT_BUILDER, 0.03f) ;
        confidenceMap.put(CardType.ENTERTAINMENT, 0.07f) ;
        confidenceMap.put(CardType.HOLIDAY, 0.08f) ;
        confidenceMap.put(CardType.HOTEL, 0.12f) ;
        confidenceMap.put(CardType.PLATINUM, 0.18f) ;
        confidenceMap.put(CardType.SHOPPING, 0.1f) ;
        confidenceMap.put(CardType.VISA_SIGNATURE,0.09f) ;

//        recommendationDetails.setRecommendationID(001);
        recommendationDetails.setCustomerID(01);
        recommendationDetails.setModelName("Model_1");
        recommendationDetails.setCardConfidenceMap(confidenceMap);

        generatedRecommendation.setCustomerID(01);
        generatedRecommendation.setModelName("Model_1");
        generatedRecommendation.setCardConfidenceMap(confidenceMap);

       assertEquals(recommendationDetails, detailsGenerator.generate(generatedRecommendation));

    }
}