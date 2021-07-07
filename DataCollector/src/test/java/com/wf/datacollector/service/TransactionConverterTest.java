package com.wf.datacollector.service;

import com.wf.datacollector.entity.InboundTransaction;
import com.wf.datacollector.filter.outbound.OutboundFilterUnknown;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionConverterTest {
    private TransactionConverter transactionConverter ;

    @BeforeEach
    public void setUp() {
        transactionConverter = new TransactionConverter();
    }

    @Test
    @DisplayName("Should return TRUE")
    public void converterTest() throws ParseException {
        InboundTransaction transaction = new InboundTransaction() ;
        transaction.setCard_num("9876543219876543");
        transaction.setMerchant("Sporer-Keebler");
        transaction.setPurchase_category("Personal");
        transaction.setTrans_amt(30.34F);
        transaction.setFirst("Ashley");
        transaction.setLast("Lopez");
        transaction.setGender("Female");
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
        transaction.setDob();

        transaction.setTrans_num("c81755dbbbea9d5c77f094348a7579be");
        transaction.setUnix_time("1371816893");
        transaction.setMerch_lat("40.49581");
        transaction.setMerch_long("-74.196111");

        assertTrue(outboundFilterUnknown.convert(transaction), "Should return TRUE");
    }

}
