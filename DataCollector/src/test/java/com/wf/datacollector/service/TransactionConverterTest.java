package com.wf.datacollector.service;

import com.wf.datacollector.entity.InboundTransaction;
import com.wf.datacollector.filter.outbound.OutboundFilterUnknown;

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
        transaction.setCardNum("9876543219876543");
        transaction.setMerchant("Sporer-Keebler");
        transaction.setPurchaseCategory("Personal");
        transaction.setTrans_amt("30.34");
        transaction.setFirst("Ashley");
        transaction.setLast("Lopez");
        transaction.setGender("Female");
        transaction.setCard_type("Shopping");
        transaction.setCredit_score("210");
        transaction.setStreet("9333 Valentine Point");
        transaction.setCity("Bellmore");
        transaction.setState("NY");
        transaction.setZip("11710");
        transaction.setLongitude("40.6729");
        transaction.setLatitude("-73.5365");
        transaction.setCity_pop("34496");
        transaction.setJob("manager");
        transaction.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("10-2-10"))

        transaction.setTrans_num("c81755dbbbea9d5c77f094348a7579be");
        transaction.setUnix_time("1371816893");
        transaction.setMerch_lat("40.49581");
        transaction.setMerch_long("-74.196111");

        assertTrue(outboundFilterUnknown.convert(transaction), "Should return TRUE");
    }

}
