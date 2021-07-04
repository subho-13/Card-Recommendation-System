package com.wf.datacollector.controller;

import com.wf.contractlib.contracts.CollectedTransaction;
import com.wf.datacollector.entity.InboundTransaction;
import com.wf.datacollector.filter.inbound.InboundFilter;
import com.wf.datacollector.service.CollectedTransactionProducer;
import com.wf.datacollector.service.TransactionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    private InboundFilter inboundFilterChain;

    @Autowired
    private TransactionConverter transactionConverter;

    @Autowired
    private CollectedTransactionProducer collectedTransactionProducer;

    @PostMapping("/transaction")
    boolean consumeTransaction(@RequestBody InboundTransaction transaction) {
        System.out.println("Consumed :: " + transaction);
        if (inboundFilterChain.isOk(transaction)) {
            CollectedTransaction collectedTransaction = transactionConverter.convert(transaction);
            return collectedTransactionProducer.produce(collectedTransaction);
        }
        return false;
    }
}
