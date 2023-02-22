package com.spring.demo.service;

import com.spring.demo.database.ProductDbManager;
import com.spring.demo.model.Product;
import com.spring.demo.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductDbManager productDbManager;

    @Override
    public List<Product> getProduct(){
        return productDbManager.getProducts();
    }
    public Object getProductNames() {
        return productDbManager.getProductNames();
    }
    @Override
    public void createProduct(Product product) throws Exception {
        productDbManager.insertProduct(product);
    }
    @Override
    public void updateProduct(Product product , int productId) throws Exception{
        productDbManager.updateProduct(product , productId);
    }

    @Override
    public Product getProductById(int productId){
       return productDbManager.getProductById(productId);
    }
}
