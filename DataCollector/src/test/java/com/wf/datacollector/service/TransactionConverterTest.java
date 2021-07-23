package com.wf.datacollector.service;

import com.wf.contractlib.contracts.CollectedTransaction;
import com.wf.contractlib.entities.*;
import com.wf.datacollector.entity.InboundTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
class TransactionConverterTest {
    private TransactionConverter transactionConverter ;

    @BeforeEach
    public void setUp() {
        transactionConverter = new TransactionConverter();
    }

    @Test
    @DisplayName("Should return TRUE")
    void converterTest() throws ParseException {
        InboundTransaction transaction = new InboundTransaction() ;
        transaction.setCard_num("9876543219876543");
        transaction.setMerchant("Sporer-Keebler");
        transaction.setPurchase_category("Personal");
        transaction.setTrans_amt(30.34F);
        transaction.setFirst("Ashley");
        transaction.setLast("Lopez");
        transaction.setGender("F");
        transaction.setCard_type("Shopping");
        transaction.setCredit_score(210);
        transaction.setStreet("9333 Valentine Point");
        transaction.setCity("Bellmore");
        transaction.setState("NY");
        transaction.setZip("11710");
        transaction.setLongitude(40.6729F);
        transaction.setLatitude(-73.5365F);
        transaction.setCity_pop(34496);
        transaction.setJob("manager");
        transaction.setDob("10-12-98");
        transaction.setTrans_num("c81755dbbbea9d5c77f094348a7579be");
        transaction.setUnix_time(1371816893L);
        transaction.setMerch_lat(40.49581F);
        transaction.setMerch_long(-74.196111F);

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


        assertEquals(transactionConverter.convert(transaction), collectedTransaction);
    }

}
