package com.wf.recommendationcompiler.configuration;

import com.wf.contractlib.contracts.GeneratedRecommendation;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

     private int concurrency = 10;

    private Map<String, Object> getConfig() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return configs;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, GeneratedRecommendation> generatedRecommendationListenerFactory() {
        JsonDeserializer<GeneratedRecommendation> jsonDeserializer =
                new JsonDeserializer<>(GeneratedRecommendation.class);
        StringDeserializer stringDeserializer = new StringDeserializer();
        DefaultKafkaConsumerFactory<String, GeneratedRecommendation> defaultKafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(getConfig(), stringDeserializer, jsonDeserializer);
        ConcurrentKafkaListenerContainerFactory<String, GeneratedRecommendation> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(defaultKafkaConsumerFactory);
         concurrentKafkaListenerContainerFactory.setConcurrency(concurrency);
        return concurrentKafkaListenerContainerFactory;
    }

     @Bean
     public NewTopic constructConsumerTopic() {
        return TopicBuilder.name("GeneratedRecommendation").partitions(concurrency).replicas(1).build();
     }
}
