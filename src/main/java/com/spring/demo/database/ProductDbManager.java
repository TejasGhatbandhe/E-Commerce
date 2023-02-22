package com.spring.demo.database;

import com.spring.demo.database.interfaces.IProductDbManager;
import com.spring.demo.mapper.ProductRowMapper;
import com.spring.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
public class ProductDbManager implements IProductDbManager {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertProduct(Product product) throws Exception {
        String QUERY = "INSERT INTO Product (name,category,price,discount,quantity) VALUES(?,?,?,?,?)";
        final int inserted = jdbcTemplate.update(QUERY, product.getName(), product.getCategory(), product.getPrice(),
                product.getDiscount(), product.getQuantity());
        if (inserted != 1) {
            throw new Exception("Failed to insert product");
        }
    }

    @Override
    public void updateProduct(Product product, int productId) throws Exception {
        String QUERY = "UPDATE Product SET name=?,category=?,price=?,discount=?,quantity=?" +
                "  WHERE productId=?";
        final int updated = jdbcTemplate.update(QUERY, product.getName(), product.getCategory(), product.getPrice(),
                product.getDiscount(), product.getQuantity(), productId);
        if (updated != 1) {
            throw new Exception("Failed to Update product");
        }

    }

    @Override
    public List<Product> getProducts() {
        String QUERY = "SELECT * FROM PRODUCT";
        return jdbcTemplate.query(QUERY, new ProductRowMapper());
    }

    public List<String> getProductNames() {
        String QUERY = "SELECT NAME FROM PRODUCT";
        return jdbcTemplate.queryForList(QUERY, String.class);
    }

    @Override
    public Product getProductById(int productId) {
        String QUERY = "Select * from Product where productId =" + productId;
        Product product = null;
        try {
            product = jdbcTemplate.queryForObject(QUERY, new ProductRowMapper());
        } catch (Exception e) {
            throw new EntityNotFoundException("Product not Found For ProductId:" + productId);
        }
        return product;
    }
}