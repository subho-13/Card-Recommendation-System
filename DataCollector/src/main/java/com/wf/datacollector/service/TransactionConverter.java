package com.wf.datacollector.service;

import com.wf.contractlib.contracts.CollectedTransaction;
import com.wf.contractlib.entities.*;
import com.wf.datacollector.entity.InboundTransaction;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TransactionConverter {
    public CollectedTransaction convert(InboundTransaction transaction) {
        CollectedTransaction collectedTransaction = new CollectedTransaction();
        collectedTransaction.setCardNum(transaction.getCard_num());
        collectedTransaction.setMerchant(transaction.getMerchant());
        collectedTransaction.setPurchaseCategory(PurchaseCategory.convert(transaction.getPurchase_category()));
        collectedTransaction.setTransactionAmount(transaction.getTrans_amt());
        collectedTransaction.setFirstName(transaction.getFirst());
        collectedTransaction.setLastName(transaction.getLast());
        collectedTransaction.setGender(Gender.convert(transaction.getGender()));
        collectedTransaction.setCardType(CardType.convert(transaction.getCard_type()));
        collectedTransaction.setCreditScore(transaction.getCredit_score());
        collectedTransaction.setStreet(transaction.getStreet());
        collectedTransaction.setCity(transaction.getCity());
        collectedTransaction.setState(transaction.getState());
        collectedTransaction.setZip(transaction.getZip());
        collectedTransaction.setAddressCoordinate(new GeoCoordinate(
                transaction.getLatitude(),
                transaction.getLongitude()
        ));
        collectedTransaction.setPopulation(transaction.getCity_pop());
        collectedTransaction.setJob(JobType.convert(transaction.getJob()));

        try {
            Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(transaction.getDob());
            collectedTransaction.setDob(dob);
        } catch (ParseException exception) {
            exception.printStackTrace();
        }

        collectedTransaction.setTransactionNum(transaction.getTrans_num());
        collectedTransaction.setUnixTime(transaction.getUnix_time());
        collectedTransaction.setMerchantCoordinate(new GeoCoordinate(
                transaction.getMerch_lat(),
                transaction.getMerch_long()
        ));
        return collectedTransaction;
    }
}
