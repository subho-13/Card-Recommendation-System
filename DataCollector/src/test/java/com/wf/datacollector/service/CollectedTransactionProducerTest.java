package com.wf.datacollector.service;

import com.wf.contractlib.contracts.CollectedTransaction;
import com.wf.contractlib.entities.*;
import com.wf.datacollector.entity.InboundTransaction;
import com.wf.datacollector.filter.inbound.InboundFilter;
import com.wf.datacollector.filter.inbound.InboundFilterNull;
import com.wf.datacollector.filter.outbound.OutboundFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CollectedTransactionProducerTest {
    private CollectedTransactionProducer collectedTransactionProducer ;

    @BeforeEach
    public void setUp() {
        collectedTransactionProducer = new CollectedTransactionProducer();
    }

    public InboundTransaction createInboundTransaction()
    {
        InboundTransaction inboundTransaction = new InboundTransaction();
        inboundTransaction.setTrans_date_trans_time("6/21/2020 12:14");
        inboundTransaction.setCard_num("9876543219876543");
        inboundTransaction.setMerchant("Sporer-Keebler");
        inboundTransaction.setPurchase_category("Personal");
        inboundTransaction.setTrans_amt(30.34F);
        inboundTransaction.setFirst("Ashley");
        inboundTransaction.setLast("Lopez");
        inboundTransaction.setGender("F");
        inboundTransaction.setCard_type("Shopping");
        inboundTransaction.setCredit_score(210);
        inboundTransaction.setStreet("9333 Valentine Point");
        inboundTransaction.setCity("Bellmore");
        inboundTransaction.setState("NY");
        inboundTransaction.setZip("11710");
        inboundTransaction.setLongitude(40.6729F);
        inboundTransaction.setLatitude(-73.5365F);
        inboundTransaction.setCity_pop(34496);
        inboundTransaction.setJob("manager");
        inboundTransaction.setDob("10-2-98");
        inboundTransaction.setTrans_num("c81755dbbbea9d5c77f094348a7579be");
        inboundTransaction.setUnix_time(1371816893L);
        inboundTransaction.setMerch_lat(40.49F);
        inboundTransaction.setMerch_long(-74.19F);
        inboundTransaction.setAge(23);
        inboundTransaction.setIs_fraud(0);

        return inboundTransaction ;
    }

    public CollectedTransaction createCollectedTransaction() throws ParseException {
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

        return collectedTransaction ;
    }

    @Test
    @DisplayName("Should return true when the fields aren't unknown")
    public void testIsOk() throws ParseException {

        CollectedTransaction collectedTransaction = createCollectedTransaction() ;
        InboundTransaction inboundTransaction = createInboundTransaction() ;

        KafkaTemplate<String, CollectedTransaction> kafkaTemplate;
        InboundFilter inboundFilterChain;
        TransactionConverter transactionConverter;
        OutboundFilter outboundFilterChain;


    }

}
