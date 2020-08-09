package com.example.demo.model;

public class Inventory {
    private final String productCode;
    private final String productName;
    private final int weight;
    private final String location;

    public Inventory(String productName, String productCode, int weight, String location){
        this.productCode = productCode;
        this.weight = weight;
        this.productName = productName;
        this.location = location;
    }

    public String getProductName(){
        return productName;
    }
    public String getProductCode(){
        return productCode;
    }

    public int getWeight() {
        return weight;
    }

    public String getLocation() {
        return location;
    }

    public String toString(){
        return productCode + " " + productName + " " + weight + " " +location;
    }
}
