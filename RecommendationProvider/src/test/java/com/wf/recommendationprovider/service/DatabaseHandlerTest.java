package com.wf.recommendationprovider.service;

import com.wf.contractlib.contracts.CompiledRecommendation;
import com.wf.contractlib.contracts.featurevector.FeatureVectorOne;
import com.wf.contractlib.entities.CardType;
import com.wf.contractlib.entities.JobType;
import com.wf.contractlib.entities.PurchaseCategory;
import com.wf.recommendationprovider.entity.CompiledRec;
import com.wf.recommendationprovider.entity.FeatureVector;
import com.wf.recommendationprovider.repository.CompiledRecRepository;
import com.wf.recommendationprovider.repository.FeatureVectorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class DatabaseHandlerTest {

    @InjectMocks
    DatabaseHandler databaseHandler;
    @Mock
    AutoCloseable closeable;
    @Mock
    private FeatureVectorRepository featureVectorRepository;
    @Mock
    private CompiledRecRepository compiledRecRepository ;
    @Mock
    private DetailsGenerator detailsGenerator;

    @BeforeEach
    void setUp() {
        databaseHandler = new DatabaseHandler();
        closeable = MockitoAnnotations.openMocks(this);

    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void featureVectorHandlerTest() {
        Map<PurchaseCategory, Float> purchaseExpenditureMap = new HashMap<>();
        purchaseExpenditureMap.put(PurchaseCategory.EDUCATION, 2345f);
        purchaseExpenditureMap.put(PurchaseCategory.ENTERTAINMENT, 1432f);
        purchaseExpenditureMap.put(PurchaseCategory.FOOD, 768.30f);
        purchaseExpenditureMap.put(PurchaseCategory.GAS_TRANS, 345f);
        purchaseExpenditureMap.put(PurchaseCategory.GROCERY_NET, 789.09f);
        purchaseExpenditureMap.put(PurchaseCategory.GROCERY_POS, 975f);
        purchaseExpenditureMap.put(PurchaseCategory.HEALTH, 2765f);
        purchaseExpenditureMap.put(PurchaseCategory.HOME, 1976f);
        purchaseExpenditureMap.put(PurchaseCategory.HOTEL, 578f);
        purchaseExpenditureMap.put(PurchaseCategory.KIDS_PETS, 2983f);
        purchaseExpenditureMap.put(PurchaseCategory.TRAVEL, 678f);
        purchaseExpenditureMap.put(PurchaseCategory.MISC_NET, 789f);
        purchaseExpenditureMap.put(PurchaseCategory.MISC_POS, 980f);
        purchaseExpenditureMap.put(PurchaseCategory.PERSONAL, 897F);
        purchaseExpenditureMap.put(PurchaseCategory.SHOP_NET, 1632f);
        purchaseExpenditureMap.put(PurchaseCategory.SHOP_POS, 532f);

        FeatureVectorOne featureVectorOne = new FeatureVectorOne();
        featureVectorOne.setCustomerID(1);
        featureVectorOne.setCardType(CardType.CASH_WISE);
        featureVectorOne.setJob(JobType.ACCOUNTANT);
        featureVectorOne.setPurchaseExpenditureMap(purchaseExpenditureMap);
        featureVectorOne.setNewUser(false);
        featureVectorOne.setCardIssueUnixTime(987654L);
        featureVectorOne.setCreditScore(360);

        FeatureVector featureVector = new FeatureVector();
        featureVector.setCustomerID(1);
        featureVector.setJob(JobType.ACCOUNTANT);
        featureVector.setCreditScore(360);
        featureVector.setNewUser(false);
        featureVector.setCardType(CardType.CASH_WISE);
        featureVector.setPurchaseExpenditureMap(purchaseExpenditureMap);

        when(detailsGenerator.generate(featureVectorOne)).thenReturn(featureVector);
        when(featureVectorRepository.findByCustomerID(anyInt())).thenReturn(Optional.of(featureVector));
        databaseHandler.handle(featureVectorOne);
        assertEquals(true, databaseHandler.getTestVariable());

        databaseHandler.setTestVariable(false);
        when(detailsGenerator.generate(featureVectorOne)).thenReturn(featureVector);
        when(featureVectorRepository.findByCustomerID(anyInt())).thenReturn(Optional.empty());
        databaseHandler.handle(featureVectorOne);
        assertEquals(false, databaseHandler.getTestVariable());
    }

    @Test
    public void CompiledRecommendationHandlerTest() {
        Map<CardType, Float> confidenceMap = new HashMap<>();
        confidenceMap.put(CardType.CASH_WISE, 0.1f);
        confidenceMap.put(CardType.COLLEGE, 0.02f);
        confidenceMap.put(CardType.CREDIT_BUILDER, 0.03f);
        confidenceMap.put(CardType.ENTERTAINMENT, 0.07f);
        confidenceMap.put(CardType.HOLIDAY, 0.08f);
        confidenceMap.put(CardType.HOTEL, 0.12f);
        confidenceMap.put(CardType.PLATINUM, 0.18f);
        confidenceMap.put(CardType.SHOPPING, 0.1f);
        confidenceMap.put(CardType.VISA_SIGNATURE, 0.09f);

        CompiledRecommendation compiledRecommendation = new CompiledRecommendation();
        compiledRecommendation.setCustomerID(1);
        compiledRecommendation.setCardConfidenceMap(confidenceMap);

        CompiledRec compiledRec = new CompiledRec() ;
        compiledRec.setCustomerID(1);
        compiledRec.setCardConfidenceMap(confidenceMap);

        when(detailsGenerator.generate(compiledRecommendation)).thenReturn(compiledRec);
        when(compiledRecRepository.findByCustomerID(anyInt())).thenReturn(Optional.of(compiledRec));
        databaseHandler.handle(compiledRecommendation);
        assertEquals(true, databaseHandler.getTestVariable());

        databaseHandler.setTestVariable(false);
        when(detailsGenerator.generate(compiledRecommendation)).thenReturn(compiledRec);
        when(compiledRecRepository.findByCustomerID(anyInt())).thenReturn(Optional.empty());
        databaseHandler.handle(compiledRecommendation);
        assertEquals(false, databaseHandler.getTestVariable());

    }


}