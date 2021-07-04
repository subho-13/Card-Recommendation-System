package com.wf.datacollector.service;

import com.wf.contractlib.contracts.CollectedTransaction;
import com.wf.datacollector.filter.outbound.OutboundFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CollectedTransactionProducer {
    @Autowired
    private KafkaTemplate<String, CollectedTransaction> kafkaTemplate;

    @Autowired
    private OutboundFilter outboundFilterChain;

    public boolean produce(CollectedTransaction collectedTransaction) {
        if (outboundFilterChain.isOk(collectedTransaction)) {
            System.out.println("Produced :: " + collectedTransaction);
            this.kafkaTemplate.send("CollectedTransaction", collectedTransaction);
            return true;
        }

        return false;
    }
}
