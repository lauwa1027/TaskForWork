package com.example.demo.dao;

import com.example.demo.model.Inventory;
import java.util.List;
import java.util.Optional;

public interface InventoryDAO {

    int insertProduct(Inventory product);

    List<Inventory> selectAllProduct();

    List<Inventory> selectProductByCode(String productCode);

    Optional<Inventory> selectProduct(String productCode, String location);

    List<Inventory> updateInventory(String startLocation, String productCode, int weight, String destination);
}
