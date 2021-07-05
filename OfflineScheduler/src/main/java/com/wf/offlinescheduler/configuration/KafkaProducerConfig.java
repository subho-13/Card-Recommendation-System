package com.wf.offlinescheduler.configuration;

import com.wf.contractlib.contracts.OfflineTrigger;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${producer-partition}")
    private int partition;

    @Value("${producer-replication}")
    private int replication;

    @Value("${producer-topic}")
    private String topic;

    @Value("${spring.kafka.bootstrap-servers}")
    private  String kafkaBootstrapServer;

    public Map<String, Object> getConfig(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServer);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configs;
    }

    @Bean
    public KafkaTemplate<String, OfflineTrigger> kafkaOfflineTriggerTemplate(){
        JsonSerializer<OfflineTrigger> jsonSerializer = new JsonSerializer<>();
        StringSerializer stringSerializer = new StringSerializer();
        DefaultKafkaProducerFactory<String, OfflineTrigger> defaultKafkaProducerFactory
                = new DefaultKafkaProducerFactory<>(getConfig(), stringSerializer, jsonSerializer);
        return new KafkaTemplate<>(defaultKafkaProducerFactory);
    }

    @Bean
    public NewTopic constructNewTopic() {
        return TopicBuilder.name(topic).partitions(partition).replicas(replication).build();
    }
}
