package com.dev.product_service.dto;

import java.math.BigDecimal;

public record ProductResponseDto(
        String name,
        String description,
        BigDecimal price
) {
}
