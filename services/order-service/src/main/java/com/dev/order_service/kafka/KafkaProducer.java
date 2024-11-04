package com.dev.order_service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendToNotification(OrderCreatedEvent orderCreatedEvent){
        log.info("Sending confirmation with body <{}>", orderCreatedEvent);
        Message<OrderCreatedEvent> message = MessageBuilder
                .withPayload(orderCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "order-placed")
                .build();

        kafkaTemplate.send(message);
    }

    public void sendToInventory(InventoryKafka inventoryKafka){
        log.info("Sending quantity with body <{}>", inventoryKafka);
        Message<InventoryKafka> message = MessageBuilder
                .withPayload(inventoryKafka)
                .setHeader(KafkaHeaders.TOPIC, "inventory-topic")
                .build();

        kafkaTemplate.send(message);
    }


}
