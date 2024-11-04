package com.dev.inventory_service.dto;

public record InventoryResponseDto(
        String skuCode,
        Integer quantity
) {
}
