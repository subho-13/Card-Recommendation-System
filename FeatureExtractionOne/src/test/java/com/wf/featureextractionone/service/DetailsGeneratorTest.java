package com.wf.featureextractionone.service;

import com.wf.contractlib.contracts.AbstractedTransaction;
import com.wf.contractlib.contracts.featurevector.FeatureVectorOne;
import com.wf.contractlib.entities.CardType;
import com.wf.contractlib.entities.GeoCoordinate;
import com.wf.contractlib.entities.JobType;
import com.wf.contractlib.entities.PurchaseCategory;
import com.wf.featureextractionone.entity.FeatureVector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DetailsGeneratorTest {
    @InjectMocks
    private DetailsGenerator detailsGenerator ;

    @Mock
    RestService restService ;

    @Mock
    AutoCloseable closeable ;

    public void setUp() {
        detailsGenerator=new DetailsGenerator() ;
        closeable = MockitoAnnotations.openMocks(this);
    }

    public void  after() throws Exception {closeable.close();}

    @Test
    public void createNewFeatureVectorTest()
    {

        AbstractedTransaction abstractedTransaction = new AbstractedTransaction();
        abstractedTransaction.setCustomerID(1);
        abstractedTransaction.setCardID(5);
        abstractedTransaction.setMerchantID(34);
        abstractedTransaction.setPurchaseCategory(PurchaseCategory.PERSONAL);
        abstractedTransaction.setMerchantCoordinate(new GeoCoordinate(40.49581F,-74.196111F));
        abstractedTransaction.setTransactionAmount(30.34F);
        abstractedTransaction.setTransactionNum("c81755dbbbea9d5c77f094348a7579be");
        abstractedTransaction.setUnixTime(1371816893L);
        abstractedTransaction.setCardType(CardType.SHOPPING);


        FeatureVector featureVector = new FeatureVector();
        featureVector.setCustomerID(1);
        featureVector.setCardType(CardType.SHOPPING);
        featureVector.setNewUser(true);
        featureVector.setCardIssueUnixTime(120037L);
        featureVector.setCreditScore(210);
        featureVector.setPurchaseExpenditureMap(new HashMap<>());
        featureVector.getPurchaseExpenditureMap().put(abstractedTransaction.getPurchaseCategory(),
                abstractedTransaction.getTransactionAmount());
        featureVector.setJob(JobType.MANAGER);

        when(restService.getCustomerJob(anyInt())).thenReturn(JobType.MANAGER) ;
        when(restService.getCustomerCreditScore(anyInt())).thenReturn(210) ;
        when(restService.getCardIssueUnixTime(anyInt())).thenReturn(120037L) ;

        assertEquals(featureVector, detailsGenerator.createNewFeatureVector(abstractedTransaction)) ;


    }

    @Test
    public void createUpdatedFeatureVectorTest()
    {

        AbstractedTransaction abstractedTransaction = new AbstractedTransaction();
        abstractedTransaction.setCustomerID(1);
        abstractedTransaction.setCardID(5);
        abstractedTransaction.setMerchantID(34);
        abstractedTransaction.setPurchaseCategory(PurchaseCategory.PERSONAL);
        abstractedTransaction.setMerchantCoordinate(new GeoCoordinate(40.49581F,-74.196111F));
        abstractedTransaction.setTransactionAmount(45F);
        abstractedTransaction.setTransactionNum("c81755dbbbea9d5c77f094348a7579be");
        abstractedTransaction.setUnixTime(1371816893L);
        abstractedTransaction.setCardType(CardType.SHOPPING);


        FeatureVector featureVector = new FeatureVector();
        featureVector.setCustomerID(1);
        featureVector.setCardType(CardType.SHOPPING);
        featureVector.setNewUser(true);
        featureVector.setCardIssueUnixTime(120037L);
        featureVector.setCreditScore(210);
        featureVector.setPurchaseExpenditureMap(new HashMap<>());
        featureVector.getPurchaseExpenditureMap().put(PurchaseCategory.PERSONAL, 30F);
        featureVector.getPurchaseExpenditureMap().put(PurchaseCategory.FOOD, 20.18F);
        featureVector.setJob(JobType.MANAGER);

        FeatureVector newFeatureVector = new FeatureVector();
        newFeatureVector.setCustomerID(featureVector.getCustomerID());
        newFeatureVector.setJob(featureVector.getJob());
        newFeatureVector.setCreditScore(featureVector.getCreditScore());
        newFeatureVector.setCardIssueUnixTime(featureVector.getCardIssueUnixTime());
        newFeatureVector.setCardType(featureVector.getCardType());
        newFeatureVector.setPurchaseExpenditureMap(new HashMap<>());
        featureVector.getPurchaseExpenditureMap().put(PurchaseCategory.PERSONAL, 75F);
        featureVector.getPurchaseExpenditureMap().put(PurchaseCategory.FOOD, 20.18F);
        featureVector.setNewUser(true);
        assertEquals(newFeatureVector, detailsGenerator.createUpdatedFeatureVector(featureVector,abstractedTransaction)) ;


    }

    @Test
    public void convertTest()
    {

        FeatureVector featureVector = new FeatureVector();
        featureVector.setCustomerID(1);
        featureVector.setCardType(CardType.SHOPPING);
        featureVector.setNewUser(true);
        featureVector.setCardIssueUnixTime(120037L);
        featureVector.setPurchaseExpenditureMap(new HashMap<>());
        featureVector.getPurchaseExpenditureMap().put(PurchaseCategory.PERSONAL, 30F);
        featureVector.getPurchaseExpenditureMap().put(PurchaseCategory.FOOD, 20.18F);
        featureVector.setJob(JobType.MANAGER);

        FeatureVectorOne featureVectorOne = new FeatureVectorOne();
        featureVectorOne.setCustomerID(1);
        featureVectorOne.setCardType(CardType.SHOPPING);
        featureVectorOne.setNewUser(true);
        featureVectorOne.setCardIssueUnixTime(120037L);
        featureVectorOne.setPurchaseExpenditureMap(new HashMap<>());
        featureVectorOne.getPurchaseExpenditureMap().put(PurchaseCategory.PERSONAL, 30F);
        featureVectorOne.getPurchaseExpenditureMap().put(PurchaseCategory.FOOD, 20.18F);
        featureVectorOne.setJob(JobType.MANAGER);

        assertEquals(featureVectorOne, detailsGenerator.convert(featureVector)) ;


    }
}
