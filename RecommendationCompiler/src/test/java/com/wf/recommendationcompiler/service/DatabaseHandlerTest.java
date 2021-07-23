package com.wf.recommendationcompiler.service;

import com.wf.contractlib.contracts.GeneratedRecommendation;
import com.wf.contractlib.entities.CardType;
import com.wf.recommendationcompiler.entity.RecommendationDetails;
import com.wf.recommendationcompiler.repository.RecommendationDetailsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class DatabaseHandlerTest {

    @InjectMocks
    DatabaseHandler databaseHandler ;
    @Mock
    private RecommendationDetailsRepository recommendationDetailsRepository;
    @Mock
    private DetailsGenerator detailsGenerator;
    @Mock
    AutoCloseable closeable ;

    @BeforeEach
    void setUp() {
        databaseHandler=new DatabaseHandler() ;
        closeable= MockitoAnnotations.openMocks(this) ;

    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    public Map<CardType,Float> getMap()
    {
        Map<CardType,Float> confidenceMap = new HashMap<>() ;
        confidenceMap.put(com.wf.contractlib.entities.CardType.CASH_WISE, 0.1f) ;
        confidenceMap.put(com.wf.contractlib.entities.CardType.COLLEGE, 0.02f) ;
        confidenceMap.put(com.wf.contractlib.entities.CardType.CREDIT_BUILDER, 0.03f) ;
        confidenceMap.put(com.wf.contractlib.entities.CardType.ENTERTAINMENT, 0.07f) ;
        confidenceMap.put(com.wf.contractlib.entities.CardType.HOLIDAY, 0.08f) ;
        confidenceMap.put(com.wf.contractlib.entities.CardType.HOTEL, 0.12f) ;
        confidenceMap.put(com.wf.contractlib.entities.CardType.PLATINUM, 0.18f) ;
        confidenceMap.put(com.wf.contractlib.entities.CardType.SHOPPING, 0.1f) ;
        confidenceMap.put(com.wf.contractlib.entities.CardType.VISA_SIGNATURE,0.09f) ;

        return confidenceMap ;
    }

    @Test
    @DisplayName("when customer is present")
     void handlerTest()
    {
        Map<CardType,Float> confidenceMap = getMap();

        RecommendationDetails recommendationDetails = new RecommendationDetails() ;
        recommendationDetails.setRecommendationID(001);
        recommendationDetails.setCustomerID(01);
        recommendationDetails.setModelName("Model_1");
        recommendationDetails.setCardConfidenceMap(confidenceMap);

        GeneratedRecommendation generatedRecommendation = new GeneratedRecommendation() ;
        generatedRecommendation.setCustomerID(01);
        generatedRecommendation.setModelName("Model_1");
        generatedRecommendation.setCardConfidenceMap(confidenceMap);

        when(detailsGenerator.generate(any())).thenReturn(recommendationDetails) ;
        when(recommendationDetailsRepository.findByCustomerIDAndModelName(anyInt(),anyString())).thenReturn(Optional.of(recommendationDetails)) ;

        databaseHandler.handle(generatedRecommendation);
        verify(recommendationDetailsRepository,times(1)).save(any());
        assertEquals(true,databaseHandler.getTestVariable());
    }

    @Test
    @DisplayName("when customer is not present")
    void detailsNullTest()
    {
        Map<CardType,Float> confidenceMap = getMap();

        RecommendationDetails recommendationDetails = new RecommendationDetails() ;
        recommendationDetails.setRecommendationID(001);
        recommendationDetails.setCustomerID(01);
        recommendationDetails.setModelName("Model_1");
        recommendationDetails.setCardConfidenceMap(confidenceMap);

        GeneratedRecommendation generatedRecommendation = new GeneratedRecommendation() ;
        generatedRecommendation.setCustomerID(01);
        generatedRecommendation.setModelName("Model_1");
        generatedRecommendation.setCardConfidenceMap(confidenceMap);

        when(detailsGenerator.generate(any())).thenReturn(recommendationDetails) ;
        when(recommendationDetailsRepository.findByCustomerIDAndModelName(anyInt(),anyString())).thenReturn(Optional.empty()) ;

        databaseHandler.handle(generatedRecommendation);
        verify(recommendationDetailsRepository,times(1)).save(any());
        assertEquals(false,databaseHandler.getTestVariable());
    }

    @Test
    void getAllRecommendationDetailsTest()
    {
        databaseHandler.getAllRecommendationDetails();
        when(recommendationDetailsRepository.findAll()).thenReturn(null) ;
        verify(recommendationDetailsRepository,times(1)).findAll() ;
    }
}