package com.wf.offlinescheduler.service;

import com.wf.contractlib.contracts.OfflineTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TriggerProducer {
    private KafkaTemplate<String, OfflineTrigger> template;

    @Autowired
    public void setTemplate(KafkaTemplate<String, OfflineTrigger> template) {
        this.template = template;
    }

    @Value("${producer-topic}")
    private String topic;

    public void produce(OfflineTrigger offlineTrigger){
        this.template.send(topic, offlineTrigger);
    }
}
