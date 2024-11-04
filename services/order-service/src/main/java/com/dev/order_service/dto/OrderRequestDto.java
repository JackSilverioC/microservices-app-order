package com.dev.order_service.dto;

import com.dev.order_service.model.UserDetails;

import java.math.BigDecimal;

public record OrderRequestDto(
        String skuCode,
        BigDecimal price,
        Integer quantity,
        UserDetails userDetails
) {
}
