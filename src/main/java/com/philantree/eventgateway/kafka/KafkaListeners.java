package com.philantree.eventgateway.kafka;

import com.philantree.eventgateway.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
class KafkaListeners {

    private final Logger LOG = LoggerFactory.getLogger(KafkaListeners.class);

    @KafkaListener(topics = "login-event-1")
    void listener(String message) {
        LOG.info("Listener [{}]", message);
    }

    @KafkaListener(topics = { "login-event-1", "login-event-2" }, groupId = "login-group-2")
    void commonListenerForMultipleTopics(String message) {
        LOG.info("MultipleTopicListener - [{}]", message);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "login-event-3", partitionOffsets = {
            @PartitionOffset(partition = "0", initialOffset = "0") }), groupId = "login-group-3")
    void listenToParitionWithOffset(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                    @Header(KafkaHeaders.OFFSET) int offset) {
        LOG.info("ListenToPartitionWithOffset [{}] from partition-{} with offset-{}", message, partition, offset);
    }

    @KafkaListener(topics = "login-event-bytes")
    void listenerForRoutingTemplate(String message) {
        LOG.info("RoutingTemplate BytesListener [{}]", message);
    }

    @KafkaListener(topics = "login-event-others")
    @SendTo("login-event-2")
    String listenAndReply(String message) {
        LOG.info("ListenAndReply [{}]", message);
        return "This is a reply sent to 'login-event-2' topic after receiving message at 'login-event-others' topic";
    }

    @KafkaListener(id = "1", topics = "login-user", groupId = "login-user-mc", containerFactory = "kafkaJsonListenerContainerFactory")
    void listenerWithMessageConverter(User user) {
        LOG.info("MessageConverterUserListener [{}]", user);
    }
}
