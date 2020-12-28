package com.philantree.eventgateway.kafka;

import com.philantree.eventgateway.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

//@Component
class InitSend {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private KafkaSender kafkaSender;

    @Autowired
    private KafkaSenderWithMessageConverter messageConverterSender;

    @Value("${com.philantree.eventgateway.kafka.topic-1}")
    private String topic1;

    @Value("${com.philantree.eventgateway.kafka.topic-2}")
    private String topic2;

    @Value("${com.philantree.eventgateway.kafka.topic-3}")
    private String topic3;

    @EventListener
    void initiateSendingMessage(ApplicationReadyEvent event) throws InterruptedException {
        Thread.sleep(5000);
        LOG.info("---------------------------------");
        kafkaSender.sendMessage("I'll be received by MultipleTopicListener, Listener & ClassLevel KafkaHandler", topic1);

        Thread.sleep(5000);
        LOG.info("---------------------------------");
        kafkaSender.sendMessage("I'll be received by ListenToPartitionWithOffset", topic3);

        Thread.sleep(5000);
        LOG.info("---------------------------------");
        kafkaSender.sendMessageWithCallback("I'll get a asyc Callback", "login-event-others");

        Thread.sleep(5000);
        LOG.info("---------------------------------");
        kafkaSender.sendMessageWithCallback("I'm sent using RoutingTemplate", "login-event-bytes");

        Thread.sleep(5000);
        LOG.info("---------------------------------");
        kafkaSender.sendMessage("I'll be ignored by RecordFilter", topic3);

        Thread.sleep(5000);
        LOG.info("---------------------------------");
        kafkaSender.sendMessage("I will get reply back from @SendTo", "login-event-others");


        Thread.sleep(5000);
        LOG.info("---------------------------------");
        kafkaSender.sendCustomMessage(new User("Lucario", "Foo", "foo@email.com"), "user-event");

        Thread.sleep(5000);
        LOG.info("---------------------------------");
        messageConverterSender.sendMessageWithConverter(new GenericMessage<>(new User("Pikachu", "Bar", "bar@email.com")));
    }
}
