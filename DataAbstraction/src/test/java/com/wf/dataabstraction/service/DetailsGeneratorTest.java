package com.wf.dataabstraction.service;

import com.wf.contractlib.contracts.CollectedTransaction;
import com.wf.contractlib.entities.*;
import com.wf.dataabstraction.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class DetailsGeneratorTest {

    private DetailsGenerator detailsGenerator ;

    @BeforeEach
    void setUp() {
        detailsGenerator = new DetailsGenerator() ;
    }

    @Test
    @DisplayName("assert return values and null parameters")
    public void test() throws ParseException {

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

        CustomerDetails customerDetails = detailsGenerator.generateCustomer(collectedTransaction) ;
        assertThrows(IllegalArgumentException.class, ()->detailsGenerator.generateCustomer(null)) ;

        CardDetails cardDetails = new CardDetails();
        cardDetails.setCardNum("9876543219876543");
        cardDetails.setCustomerDetails(customerDetails);
        cardDetails.setCardType(CardType.SHOPPING);
        cardDetails.setCardIssueUnixTime(1371816893L);
        assertEquals(cardDetails,detailsGenerator.generateCard(collectedTransaction,customerDetails)) ;
        assertThrows(IllegalArgumentException.class, ()->detailsGenerator.generateCard(collectedTransaction,null)) ;

        CityDetails cityDetails = detailsGenerator.generateCity(collectedTransaction);

        AddressDetails addressDetails = new AddressDetails() ;
        addressDetails.setCustomerDetails(customerDetails);
        addressDetails.setCityDetails(cityDetails);
        addressDetails.setState("NY");
        addressDetails.setZip("11710");
        addressDetails.setAddressCoordinate(new GeoCoordinate( -73.5365F,40.6729F));

        assertEquals(addressDetails, detailsGenerator.generateAddress(collectedTransaction,customerDetails,cityDetails)) ;
        assertThrows(IllegalArgumentException.class, ()->detailsGenerator.generateAddress(null,customerDetails,null)) ;

        MerchantDetails merchantDetails = new MerchantDetails();
        merchantDetails.setMerchantName("Sporer-Keebler");

        assertEquals(merchantDetails, detailsGenerator.getMerchant(collectedTransaction)) ;
        assertThrows(IllegalArgumentException.class, ()->detailsGenerator.getMerchant(null)) ;
    }
}