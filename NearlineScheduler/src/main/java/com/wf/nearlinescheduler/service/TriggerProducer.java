package com.wf.nearlinescheduler.service;

import com.wf.contractlib.contracts.NearlineTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TriggerProducer {
    private KafkaTemplate<String, NearlineTrigger> template;

    @Autowired
    public void setTemplate(KafkaTemplate<String, NearlineTrigger> template) {
        this.template = template;
    }

    @Value("${producer-topic}")
    private String topic;

    public void produce(NearlineTrigger nearlineTrigger){
        System.out.println("Produced :: " + nearlineTrigger);
        this.template.send(topic, nearlineTrigger);
    }
}