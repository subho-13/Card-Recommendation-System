package com.wf.recommendationprovider.configuration;

import com.wf.contractlib.contracts.CompiledRecommendation;
import com.wf.contractlib.contracts.featurevector.FeatureVectorOne;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    private static final int concurrency = 1;

    private Map<String, Object> getConfig() {
        Map<String, Object> configs = new ConcurrentHashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return configs;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeatureVectorOne> featureVectorOneListenerFactory() {
        JsonDeserializer<FeatureVectorOne> jsonDeserializer =
                new JsonDeserializer<>(FeatureVectorOne.class);
        StringDeserializer stringDeserializer = new StringDeserializer();
        DefaultKafkaConsumerFactory<String, FeatureVectorOne> defaultKafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(getConfig(), stringDeserializer, jsonDeserializer);
        ConcurrentKafkaListenerContainerFactory<String, FeatureVectorOne> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(defaultKafkaConsumerFactory);
        concurrentKafkaListenerContainerFactory.setConcurrency(concurrency);
        return concurrentKafkaListenerContainerFactory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CompiledRecommendation> compiledRecommendationListenerFactory() {
        JsonDeserializer<CompiledRecommendation> jsonDeserializer =
                new JsonDeserializer<>(CompiledRecommendation.class);
        StringDeserializer stringDeserializer = new StringDeserializer();
        DefaultKafkaConsumerFactory<String, CompiledRecommendation> defaultKafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(getConfig(), stringDeserializer, jsonDeserializer);
        ConcurrentKafkaListenerContainerFactory<String, CompiledRecommendation> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(defaultKafkaConsumerFactory);
        concurrentKafkaListenerContainerFactory.setConcurrency(concurrency);
        return concurrentKafkaListenerContainerFactory;
    }
}
