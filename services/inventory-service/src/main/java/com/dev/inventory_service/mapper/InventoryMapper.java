package com.dev.inventory_service.mapper;

import com.dev.inventory_service.dto.InventoryRequestDto;
import com.dev.inventory_service.dto.InventoryResponseDto;
import com.dev.inventory_service.model.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public InventoryResponseDto fromInventoryToInventoryResponseDto(Inventory inventory){
        return new InventoryResponseDto(
                inventory.getSkuCode(),
                inventory.getQuantity()
        );
    }

    public Inventory fromInventoryRequestDtoToInventory(InventoryRequestDto inventoryRequestDto){
        return Inventory.builder()
                .skuCode(inventoryRequestDto.skuCode())
                .quantity(inventoryRequestDto.quantity())
                .build();
    }
}
