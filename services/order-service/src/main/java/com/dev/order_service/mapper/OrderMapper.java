package com.dev.order_service.mapper;

import com.dev.order_service.dto.OrderRequestDto;
import com.dev.order_service.dto.OrderResponseDto;
import com.dev.order_service.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponseDto fromOrderToOrderResponseDto(Order order){
        return new OrderResponseDto(
                order.getOrderNumber(),
                order.getSkuCode(),
                order.getPrice(),
                order.getQuantity()
        );
    }

    public Order fromOrderRequestDtoToOrder(OrderRequestDto orderRequestDto){
        return Order.builder()
                .skuCode(orderRequestDto.skuCode())
                .price(orderRequestDto.price())
                .quantity(orderRequestDto.quantity())
                .build();
    }
}
