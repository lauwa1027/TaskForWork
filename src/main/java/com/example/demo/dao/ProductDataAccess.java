package com.example.demo.dao;

import com.example.demo.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgres")
public class ProductDataAccess implements InventoryDAO{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertProduct(Inventory product) {
        String sql = "" +
                "INSERT INTO inventory (" +
                " productcode, " +
                " productname, " +
                " weight, " +
                " location) " +
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                product.getProductName(),
                product.getProductCode(),
                product.getWeight(),
                product.getLocation());

    }



    @Override
    public List<Inventory> selectAllProduct() {
        final String sql = "SELECT * FROM inventory";
        List<Inventory> products = jdbcTemplate.query(sql, ((resultSet, i) -> {
            String name =  resultSet.getString("productName");
            String code = resultSet.getString("productCode");
            int weight = resultSet.getInt("weight");
            String location = resultSet.getString("location");
            return new Inventory(name, code, weight, location);
        }));
        return products;
    }

    @Override
    public List<Inventory> selectProductByCode(String productCode) {
        final String sql = "SELECT productcode, productname, weight, location FROM inventory WHERE productcode = ?";
        return jdbcTemplate.query(sql, new Object[]{productCode}, ((resultSet, i) -> {
            String name =  resultSet.getString("productName");
            String code = resultSet.getString("productCode");
            int weight = resultSet.getInt("weight");
            String location = resultSet.getString("location");
            return new Inventory(name, code, weight, location);
        }));
    }

    @Override
    public Optional<Inventory> selectProduct(String productCode, String location) {
        System.out.println((productCode));
        System.out.println((location));
        final String sql = "SELECT productcode, productname, weight, location FROM inventory WHERE productcode = ? AND location = ? ";
        Inventory product = jdbcTemplate.queryForObject(sql, new Object[]{productCode, location}, ((resultSet, i) -> {
            String name =  resultSet.getString("productName");
            String code = resultSet.getString("productCode");
            int weight = resultSet.getInt("weight");
            String locat = resultSet.getString("location");
            return new Inventory(name, code, weight, locat);
        }));
        return Optional.ofNullable(product);
    }

    @Override
    public List<Inventory> updateInventory(String startLocation, String productCode, int number, String destination) {

        Optional<Inventory> start = selectProduct(productCode, startLocation);
        Optional<Inventory> end = selectProduct(productCode, destination);
        int sNumb = start.get().getWeight() - number;
        int eNumb = end.get().getWeight() + number;

        final String sql = "" +
                "UPDATE inventory " +
                "SET weight = ? " +
                "WHERE productCode = ? AND location = ?";
        int s = jdbcTemplate.update(sql, sNumb, start.get().getProductCode(), start.get().getLocation());
        int e = jdbcTemplate.update(sql, eNumb, end.get().getProductCode(), end.get().getLocation());

        return selectProductByCode(productCode);
    }
}
