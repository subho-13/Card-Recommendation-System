package com.wf.datacollector.controller;

import com.wf.datacollector.entity.InboundTransaction;
import com.wf.datacollector.service.CollectedTransactionProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    private CollectedTransactionProducer collectedTransactionProducer;

    @Autowired
    public void setCollectedTransactionProducer(CollectedTransactionProducer collectedTransactionProducer) {
        this.collectedTransactionProducer = collectedTransactionProducer;
    }

    @PostMapping("/transaction")
    public boolean consumeTransaction(@RequestBody InboundTransaction inboundTransaction) {
        return collectedTransactionProducer.produce(inboundTransaction);
    }
}
