package com.philantree.eventgateway.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaTopicConfig {
    @Value("${com.philantree.eventgateway.kafka.topic-1}")
    private String topic1;

    @Value("${com.philantree.eventgateway.kafka.topic-2}")
    private String topic2;

    @Value("${com.philantree.eventgateway.kafka.topic-3}")
    private String topic3;

    @Value("${com.philantree.eventgateway.kafka.topic-4}")
    private String topic4;

    @Bean
    NewTopic topic1() {
        return TopicBuilder.name(topic1)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    NewTopic topic2() {
        return TopicBuilder.name(topic2)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    NewTopic topic3() {
        return TopicBuilder.name(topic3)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    NewTopic topicUser() {
        return TopicBuilder.name(topic4)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    NewTopic topicBytes() {
        return TopicBuilder.name("login-event-bytes")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    NewTopic others() {
        return TopicBuilder.name("login-event-others")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
