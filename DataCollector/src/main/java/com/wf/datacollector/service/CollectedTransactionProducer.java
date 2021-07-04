package com.wf.datacollector.service;

import com.wf.contractlib.contracts.CollectedTransaction;
import com.wf.datacollector.entity.InboundTransaction;
import com.wf.datacollector.filter.inbound.InboundFilter;
import com.wf.datacollector.filter.outbound.OutboundFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CollectedTransactionProducer {
    @Autowired
    private KafkaTemplate<String, CollectedTransaction> kafkaTemplate;

    @Autowired
    private InboundFilter inboundFilterChain;

    @Autowired
    private TransactionConverter transactionConverter;

    @Autowired
    private OutboundFilter outboundFilterChain;

    public boolean produce(InboundTransaction inboundTransaction) {
        if (inboundFilterChain.isOk(inboundTransaction)) {
            CollectedTransaction collectedTransaction = transactionConverter.convert(inboundTransaction);
            if (outboundFilterChain.isOk(collectedTransaction)) {
                sendOverKafka(collectedTransaction);
                return true;
            }
        }

        return false;
    }

    private void sendOverKafka(CollectedTransaction collectedTransaction) {
        this.kafkaTemplate.send("CollectedTransaction", collectedTransaction);
    }
}
