package com.wf.dataabstraction.service;

import com.wf.contractlib.contracts.AbstractedTransaction;
import com.wf.contractlib.contracts.CollectedTransaction;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransactionConverter {

    public AbstractedTransaction convert(@NotNull CollectedTransaction collectedTransaction, @NotNull Map<String,
            Integer> mapDetailsID){
        AbstractedTransaction abstractedTransaction = new AbstractedTransaction();
        abstractedTransaction.setCustomerID(mapDetailsID.get("customerID"));
        abstractedTransaction.setCardID(mapDetailsID.get("cardID"));
        abstractedTransaction.setMerchantID(mapDetailsID.get("merchantID"));
        abstractedTransaction.setPurchaseCategory(collectedTransaction.getPurchaseCategory());
        abstractedTransaction.setMerchantCoordinate(collectedTransaction.getMerchantCoordinate());
        abstractedTransaction.setTransactionAmount(collectedTransaction.getTransactionAmount());
        abstractedTransaction.setTransactionNum(collectedTransaction.getTransactionNum());
        abstractedTransaction.setUnixTime(collectedTransaction.getUnixTime());
        abstractedTransaction.setCardType(collectedTransaction.getCardType());
        return abstractedTransaction;
    }
}