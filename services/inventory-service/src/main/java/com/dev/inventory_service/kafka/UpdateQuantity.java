package com.dev.inventory_service.kafka;

import com.dev.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateQuantity {

    private final InventoryRepository inventoryRepository;

    @KafkaListener(topics = "inventory-topic", groupId = "inventoryService")
    public void listen(InventoryKafka inventoryKafka){
        log.info("Consuming: {}",inventoryKafka);
        var productFound = inventoryRepository.findBySkuCode(inventoryKafka.skuCode());
        productFound.setQuantity(productFound.getQuantity() - inventoryKafka.quantity());
        inventoryRepository.save(productFound);
    }
}
