package com.wf.recommendationcompiler.service;

import com.wf.contractlib.entities.CardType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FinalRecommendationCompilerTest {

    @InjectMocks
    private FinalRecommendationCompiler finalRecommendationCompiler;
    private Object NullPointerException;

    @BeforeEach
    void setUp() {
        finalRecommendationCompiler = new FinalRecommendationCompiler();
    }

    @Test
    @DisplayName("final card confidence map is returned successfully ")
    public void getCardConfidenceMapTest() {
        Map<CardType, Float> model1_map = new HashMap<>();
        Map<CardType, Float> model2_map = new HashMap<>();
        Map<CardType, Float> model3_map = new HashMap<>();

        model1_map.put(CardType.CASH_WISE, 0.1f);
        model1_map.put(CardType.COLLEGE, 0.02f);
        model1_map.put(CardType.CREDIT_BUILDER, 0.03f);
        model1_map.put(CardType.ENTERTAINMENT, 0.07f);
        model1_map.put(CardType.HOLIDAY, 0.08f);
        model1_map.put(CardType.HOTEL, 0.12f);
        model1_map.put(CardType.PLATINUM, 0.18f);
        model1_map.put(CardType.SHOPPING, 0.1f);
        model1_map.put(CardType.VISA_SIGNATURE, 0.09f);

        model2_map.put(CardType.CASH_WISE, 0.08f);
        model2_map.put(CardType.COLLEGE, 0.01f);
        model2_map.put(CardType.CREDIT_BUILDER, 0.1f);
        model2_map.put(CardType.ENTERTAINMENT, 0.23f);
        model2_map.put(CardType.HOLIDAY, 0.12f);
        model2_map.put(CardType.HOTEL, 0.09f);
        model2_map.put(CardType.PLATINUM, 0.43f);
        model2_map.put(CardType.SHOPPING, 0.1f);
        model2_map.put(CardType.VISA_SIGNATURE, 0.6f);

        model3_map.put(CardType.CASH_WISE, 0.22f);
        model3_map.put(CardType.COLLEGE, 0.09f);
        model3_map.put(CardType.CREDIT_BUILDER, 0.01f);
        model3_map.put(CardType.ENTERTAINMENT, 0.08f);
        model3_map.put(CardType.HOLIDAY, 0.076f);
        model3_map.put(CardType.HOTEL, 0.43f);
        model3_map.put(CardType.PLATINUM, 0.5f);
        model3_map.put(CardType.SHOPPING, 0.082f);
        model3_map.put(CardType.VISA_SIGNATURE, 0.21f);

        Map<String, Map<CardType, Float>> modelCardConfidenceMap = new HashMap<>();
        modelCardConfidenceMap.put("Rule Learning", model1_map);
        modelCardConfidenceMap.put("Association Rule Learning", model2_map);
        modelCardConfidenceMap.put("SOM Based", model3_map);

        Map<CardType, Float> finalCardConfidencemap = new HashMap<>();

        finalCardConfidencemap.put(CardType.CASH_WISE, 0.065f);
        finalCardConfidencemap.put(CardType.COLLEGE, 0.0155f);
        finalCardConfidencemap.put(CardType.CREDIT_BUILDER, 0.0405f);
        finalCardConfidencemap.put(CardType.ENTERTAINMENT, 0.099f);
        finalCardConfidencemap.put(CardType.HOLIDAY, 0.0616f);
        finalCardConfidencemap.put(CardType.HOTEL, 0.0925f);
        finalCardConfidencemap.put(CardType.PLATINUM, 0.22749999f);
        finalCardConfidencemap.put(CardType.SHOPPING, 0.0582f);
        finalCardConfidencemap.put(CardType.VISA_SIGNATURE, 0.24450001f);

        assertEquals(finalCardConfidencemap, finalRecommendationCompiler.getCardConfidenceMap(modelCardConfidenceMap));

    }


    @Test
    @DisplayName("Model weights are updated successfully ")
    public void updateMapTest() {
        HashMap<String, Float> modelWeightMap = new HashMap<>();
        modelWeightMap.put("Rule Learning", 0.05F);
        modelWeightMap.put("Association Rule Learning", 0.15F);
        modelWeightMap.put("Kpca Kmeans", 0.35F);
        modelWeightMap.put("Kpca Similarity", 0.06F);
        modelWeightMap.put("Pca Kmeans", 0.04F);
        modelWeightMap.put("Som Based", 0.1F);
        modelWeightMap.put("Neural Based", 0.1F);
        modelWeightMap.put("Autoencoder Based", 0.1F);
        modelWeightMap.put("New User", 0.05F);

        finalRecommendationCompiler.updateModelWeightMap(modelWeightMap);
        assertEquals(modelWeightMap, finalRecommendationCompiler.getModelWeightMap());
    }
}