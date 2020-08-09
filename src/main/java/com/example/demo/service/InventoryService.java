package com.example.demo.service;

import com.example.demo.dao.ProductDataAccess;
import com.example.demo.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final ProductDataAccess productDataAccess;

    @Autowired
    public InventoryService(@Qualifier("postgres") ProductDataAccess productDataAccess) {
        this.productDataAccess = productDataAccess;
    }

    public int addProduct(Inventory product){
        return productDataAccess.insertProduct(product);
    }

    public List<Inventory> getAllProduct(){
        return productDataAccess.selectAllProduct();
    }

    public List<Inventory> getProductByCode(String code){
        return productDataAccess.selectProductByCode(code);
    }

    public Optional<Inventory> getProduct(String code, String location){
        return productDataAccess.selectProduct(code, location);
    }
    public List<Inventory> updateInventory(String startLocation, String productCode, int number, String destination ){
        return productDataAccess.updateInventory(startLocation, productCode, number, destination);
    }
}
