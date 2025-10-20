package com.m4gpie.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m4gpie.inventory_service.models.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
