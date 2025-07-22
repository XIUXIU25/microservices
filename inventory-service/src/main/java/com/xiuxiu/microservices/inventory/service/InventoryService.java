package com.xiuxiu.microservices.inventory.service;

import com.xiuxiu.microservices.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean isInstock(String skuCode, Integer quantity) {
        //Find an inventory for a given skuCode where quantity>=0
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode,quantity);
    }
}
