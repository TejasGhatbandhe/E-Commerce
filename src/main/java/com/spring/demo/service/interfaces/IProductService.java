package com.spring.demo.service.interfaces;

import com.spring.demo.model.Product;

import java.util.List;

public interface IProductService {

    List<Product> getProduct() throws Exception;
    void createProduct(Product product) throws Exception;

    void updateProduct(Product product, int productId) throws Exception;

    Product getProductById(int productId) throws Exception;
}
