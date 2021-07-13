package com.wf.offlinescheduler.service;

import com.wf.contractlib.contracts.OfflineTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TriggerProducer {
    private KafkaTemplate<String, OfflineTrigger> template;
    @Value("${producer-topic}")
    private String topic;

    @Autowired
    public void setTemplate(KafkaTemplate<String, OfflineTrigger> template) {
        this.template = template;
    }

    public void produce(OfflineTrigger offlineTrigger) {
        System.out.println(offlineTrigger);
        this.template.send(topic, offlineTrigger);
    }
}
