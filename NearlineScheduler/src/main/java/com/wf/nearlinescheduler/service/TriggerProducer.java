package com.wf.nearlinescheduler.service;

import com.wf.contractlib.contracts.NearlineTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TriggerProducer {
    @Autowired
    private KafkaTemplate<String, NearlineTrigger> template;

    @Value("${producer-topic}")
    private String topic;

    public void produceScheduler(NearlineTrigger nearlineTrigger){
        this.template.send(topic, nearlineTrigger);
    }
}