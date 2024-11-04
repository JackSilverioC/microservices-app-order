package com.dev.order_service.dto;

import java.math.BigDecimal;

public record OrderResponseDto(
        String orderNumber,
        String skuCode,
        BigDecimal price,
        Integer quantity
) {
}
