package com.wf.featureextractionone.service;

import com.wf.contractlib.contracts.featurevector.FeatureVectorOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FeatureVectorOneProducer {
    private KafkaTemplate<String, FeatureVectorOne> template;
    @Value("${producer-topic}")
    private String topic;

    @Autowired
    public void setTemplate(KafkaTemplate<String, FeatureVectorOne> template) {
        this.template = template;
    }

    public void produce(FeatureVectorOne featureVectorOne) {
        System.out.println(featureVectorOne);
        this.template.send(topic, featureVectorOne);
    }
}