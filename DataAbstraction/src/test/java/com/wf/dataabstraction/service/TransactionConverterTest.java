package com.wf.dataabstraction.service;

import com.wf.contractlib.contracts.AbstractedTransaction;
import com.wf.contractlib.contracts.CollectedTransaction;
import com.wf.contractlib.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TransactionConverterTest {
    private TransactionConverter transactionConverter ;

    @BeforeEach
    public void setUp(){ transactionConverter=new TransactionConverter() ;}

    @Test
     void converterTest() throws ParseException {
        CollectedTransaction collectedTransaction = new CollectedTransaction() ;
        collectedTransaction.setCardNum("9876543219876543");
        collectedTransaction.setMerchant("Sporer-Keebler");
        collectedTransaction.setPurchaseCategory(PurchaseCategory.PERSONAL);
        collectedTransaction.setTransactionAmount(30.34F);
        collectedTransaction.setFirstName("Ashley");
        collectedTransaction.setLastName("Lopez");
        collectedTransaction.setGender(Gender.FEMALE);
        collectedTransaction.setCardType(CardType.SHOPPING);
        collectedTransaction.setCreditScore(210);
        collectedTransaction.setStreet("9333 Valentine Point");
        collectedTransaction.setCity("Bellmore");
        collectedTransaction.setState("NY");
        collectedTransaction.setZip("11710");
        collectedTransaction.setAddressCoordinate(new GeoCoordinate( -73.5365F,40.6729F));
        collectedTransaction.setPopulation(34496);
        collectedTransaction.setJob(JobType.MANAGER);
        collectedTransaction.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("10-12-98"));
        collectedTransaction.setTransactionNum("c81755dbbbea9d5c77f094348a7579be");
        collectedTransaction.setUnixTime(1371816893L);
        collectedTransaction.setMerchantCoordinate(new GeoCoordinate(40.49581F,-74.196111F));

        Map<String, Integer> mapDetailsID = new HashMap<>() ;
        mapDetailsID.put("customerID",1) ;
        mapDetailsID.put("cardID",5) ;
        mapDetailsID.put("merchantID",34) ;


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

        assertEquals(abstractedTransaction, transactionConverter.convert(collectedTransaction,mapDetailsID)) ;
    }


}