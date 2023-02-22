package com.spring.demo.mapper;

import com.spring.demo.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(final ResultSet result, final int rowNum) throws SQLException {
        final Product product = new Product();
        product.setProductId(result.getInt("productId"));
        product.setName(result.getString("name"));
        product.setCategory(result.getString("category"));
        product.setDiscount(result.getFloat("discount"));
        product.setQuantity(result.getInt("quantity"));
        product.setPrice(result.getDouble("price"));
        return product;
    }
}
