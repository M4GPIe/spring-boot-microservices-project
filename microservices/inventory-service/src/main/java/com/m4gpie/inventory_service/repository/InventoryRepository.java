package com.m4gpie.inventory_service.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.m4gpie.inventory_service.models.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
