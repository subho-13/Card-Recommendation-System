package com.wf.dataabstraction.service;

import com.wf.contractlib.contracts.AbstractedTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AbstractedTransactionProducer {
    private KafkaTemplate<String, AbstractedTransaction> template;

    @Autowired
    public void setTemplate(KafkaTemplate<String, AbstractedTransaction> template) {
        this.template = template;
    }

    @Value("${producer-topic}")
    private String topic;

    public void produce(AbstractedTransaction abstractedTransaction){
        this.template.send(topic, abstractedTransaction);
    }
}
