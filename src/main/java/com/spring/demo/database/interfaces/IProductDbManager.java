package com.spring.demo.database.interfaces;

import com.spring.demo.model.Product;

import java.util.List;

public interface IProductDbManager {
    void insertProduct(Product product) throws Exception;
    void updateProduct(Product product, int productId) throws Exception;

    List<Product> getProducts();

    Product getProductById(int productId);
}
