package com.dev.order_service.service;

import com.dev.order_service.client.InventoryClient;
import com.dev.order_service.dto.OrderRequestDto;
import com.dev.order_service.dto.OrderResponseDto;
import com.dev.order_service.kafka.InventoryKafka;
import com.dev.order_service.kafka.KafkaProducer;
import com.dev.order_service.kafka.OrderCreatedEvent;
import com.dev.order_service.mapper.OrderMapper;
import com.dev.order_service.model.Order;
import com.dev.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryClient inventoryClient;
    private final KafkaProducer kafkaProducer;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {

        Boolean isProductInStock = this.inventoryClient.isInStock(
                orderRequestDto.skuCode(), orderRequestDto.quantity()
        ).orElseThrow();

        if (isProductInStock){
            Order order = orderRepository.save(
                    orderMapper.fromOrderRequestDtoToOrder(orderRequestDto)
            );
            log.info("Orden creada: {}", order);

            OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(
                    order.getOrderNumber(),
                    orderRequestDto.userDetails().email()
            );
            kafkaProducer.sendToNotification(orderCreatedEvent);
            kafkaProducer.sendToInventory(
                    new InventoryKafka(
                            orderRequestDto.skuCode(),
                            orderRequestDto.quantity()
                    )
            );

            return orderMapper.fromOrderToOrderResponseDto(order);
        } else {
            throw new RuntimeException("Product " + orderRequestDto.skuCode() +
                    ", have insufficient stock");
        }

    }

}
