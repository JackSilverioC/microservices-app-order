package com.dev.order_service.service;

import com.dev.order_service.dto.OrderRequestDto;
import com.dev.order_service.dto.OrderResponseDto;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);

}
