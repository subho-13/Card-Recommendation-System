package com.wf.datacollector.service;

import com.wf.contractlib.contracts.CollectedTransaction;
import com.wf.datacollector.entity.InboundTransaction;
import com.wf.datacollector.filter.inbound.InboundFilter;
import com.wf.datacollector.filter.outbound.OutboundFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CollectedTransactionProducer {
    private KafkaTemplate<String, CollectedTransaction> kafkaTemplate;
    private InboundFilter inboundFilterChain;
    private TransactionConverter transactionConverter;
    private OutboundFilter outboundFilterChain;

    @Value("${producer-topic}")
    private String topic;

    @Autowired
    public void setInboundFilterChain(InboundFilter inboundFilterChain) {
        this.inboundFilterChain = inboundFilterChain;
    }

    @Autowired
    public void setKafkaTemplate(KafkaTemplate<String, CollectedTransaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    public void setOutboundFilterChain(OutboundFilter outboundFilterChain) {
        this.outboundFilterChain = outboundFilterChain;
    }

    @Autowired
    public void setTransactionConverter(TransactionConverter transactionConverter) {
        this.transactionConverter = transactionConverter;
    }

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
        this.kafkaTemplate.send(topic, collectedTransaction);
    }
}
