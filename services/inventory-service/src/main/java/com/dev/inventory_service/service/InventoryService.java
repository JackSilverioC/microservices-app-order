package com.dev.inventory_service.service;

public interface InventoryService {

    boolean isInStock(String skuCode, Integer quantity);
}
