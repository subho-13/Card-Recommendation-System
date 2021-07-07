package com.wf.datacollector.filter.inbound;

import com.wf.contractlib.contracts.CollectedTransaction;
import com.wf.contractlib.entities.CardType;
import com.wf.contractlib.entities.Gender;
import com.wf.contractlib.entities.JobType;
import com.wf.contractlib.entities.PurchaseCategory;
import com.wf.datacollector.entity.InboundTransaction;
import com.wf.datacollector.filter.outbound.OutboundFilterUnknown;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InboundFilterNullTest {

    private  InboundFilterNull inboundFilterNull ;

    @BeforeEach
    public void setUp() {
        inboundFilterNull = new InboundFilterNull();
        inboundFilterNull.setNextFilter(null);
    }

    @Test
    @DisplayName("Should return true when the fields aren't unknown")
    public void testIsOk() throws ParseException {
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

        System.out.println(inboundTransaction) ;
        assertTrue(inboundFilterNull.isOk(inboundTransaction), "Should return true");
    }


    @Test
    @DisplayName("Should return false when dob is null")
    public void testIsNotOkWhenDobNull() {
        InboundTransaction inboundTransaction = new InboundTransaction();
        inboundTransaction.setDob(null);
        inboundTransaction.setGender(null);
        inboundTransaction.setJob("Accountant");
        inboundTransaction.setCard_type("Cash_wise");
        inboundTransaction.setPurchase_category("Education");

        assertFalse(inboundFilterNull.isOk(inboundTransaction), "Should return false");
    }

    @Test
    @DisplayName("Should return false when the fields are unknown")
    public void testIsNotOkWhenUnknown() throws ParseException {
        InboundTransaction inboundTransaction = new InboundTransaction();
        inboundTransaction.setCard_num("9876543219876543");
        inboundTransaction.setMerchant("Sporer-Keebler");
        inboundTransaction.setPurchase_category("Personal");
        inboundTransaction.setTrans_amt(30.34F);
        inboundTransaction.setFirst("Ashley");
        inboundTransaction.setLast("Lopez");
        inboundTransaction.setGender("");
        inboundTransaction.setCard_type("");
        inboundTransaction.setCredit_score(210);
        inboundTransaction.setStreet("");
        inboundTransaction.setCity("Bellmore");
        inboundTransaction.setState("");
        inboundTransaction.setZip("11710");
        inboundTransaction.setLongitude(40.6729F);
        inboundTransaction.setLatitude(-73.5365F);
        inboundTransaction.setCity_pop(34496);
        inboundTransaction.setJob("manager");
        inboundTransaction.setDob("10-2-10");
        inboundTransaction.setTrans_num("c81755dbbbea9d5c77f094348a7579be");
        inboundTransaction.setUnix_time(1371816893L);
        inboundTransaction.setMerch_lat(40.49F);
        inboundTransaction.setMerch_long(-74.19F);

        System.out.println(inboundTransaction) ;
        assertFalse(inboundFilterNull.isOk(inboundTransaction), "Should return false");
    }
}
