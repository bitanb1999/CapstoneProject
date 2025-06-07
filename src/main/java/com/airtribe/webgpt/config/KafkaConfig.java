package com.airtribe.webgpt.config;

import com.airtribe.webgpt.model.CrawledContent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic crawlTopic() {
        return TopicBuilder.name("crawl-topic")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public KafkaTemplate<String, CrawledContent> kafkaTemplate(ProducerFactory<String, CrawledContent> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}