package com.wf.datacollector.filter.outbound;

import com.wf.contractlib.contracts.CollectedTransaction;
import com.wf.contractlib.entities.CardType;
import com.wf.contractlib.entities.Gender;
import com.wf.contractlib.entities.JobType;
import com.wf.contractlib.entities.PurchaseCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OutboundFilterUnknownTest {
    private OutboundFilterUnknown outboundFilterUnknown;

    @BeforeEach
    public void setUp() {
        outboundFilterUnknown = new OutboundFilterUnknown();
        outboundFilterUnknown.setNextFilter(null);
    }


    @Test
    @DisplayName("Should return true when the fields aren't unknown")
     void testIsOk() throws ParseException {
        CollectedTransaction collectedTransaction = new CollectedTransaction();
        collectedTransaction.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("10-2-10"));
        collectedTransaction.setGender(Gender.FEMALE);
        collectedTransaction.setJob(JobType.ACCOUNTANT);
        collectedTransaction.setCardType(CardType.CASH_WISE);
        collectedTransaction.setPurchaseCategory(PurchaseCategory.EDUCATION);

        assertTrue(outboundFilterUnknown.isOk(collectedTransaction), "Should return true");
    }


    @Test
    @DisplayName("Should return false when dob is null")
     void testIsNotOkWhenDobNull() {
        CollectedTransaction collectedTransaction = new CollectedTransaction();
        collectedTransaction.setDob(null);
        collectedTransaction.setGender(Gender.FEMALE);
        collectedTransaction.setJob(JobType.ACCOUNTANT);
        collectedTransaction.setCardType(CardType.CASH_WISE);
        collectedTransaction.setPurchaseCategory(PurchaseCategory.EDUCATION);

        assertFalse(outboundFilterUnknown.isOk(collectedTransaction), "Should return false");
    }

    @Test
    @DisplayName("Should return false when the fields are unknown")
     void testIsNotOkWhenUnknown() throws ParseException {
        CollectedTransaction collectedTransaction = new CollectedTransaction();
        collectedTransaction.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("10-2-10"));
        collectedTransaction.setGender(Gender.UNKNOWN);
        collectedTransaction.setJob(JobType.ACCOUNTANT);
        collectedTransaction.setCardType(CardType.CASH_WISE);
        collectedTransaction.setPurchaseCategory(PurchaseCategory.EDUCATION);

        assertFalse(outboundFilterUnknown.isOk(collectedTransaction), "Should return false");
    }
}
