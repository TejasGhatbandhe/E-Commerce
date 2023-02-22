package com.spring.demo.controller;

import com.spring.demo.model.Product;
import com.spring.demo.model.ResponseModel;
import com.spring.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseModel getAllProducts() {
        ResponseModel response = new ResponseModel();
        response.message = "Product List";
        response.responseCode = HttpStatus.OK.value();
        return response;
    }
    @GetMapping("/products/{productId}")
    public ResponseModel getProductById(@PathVariable int productId) {
        ResponseModel response = new ResponseModel();
        response.data = Collections.singletonList(productService.getProductById(productId));
        response.message = "Product List";
        response.responseCode = HttpStatus.OK.value();
        return response;
    }
    @GetMapping("/productNames")
    public ResponseModel getAllProductNames() {
        ResponseModel response = new ResponseModel();
        try {
            response.data = Collections.singletonList(productService.getProductNames());
        } catch(Exception e) {
            response.message = "Products Not Found";
            response.responseCode = HttpStatus.NOT_FOUND.value();
            return response;
        }
        response.message = "Product List";
        response.responseCode = HttpStatus.OK.value();
        return response;
    }


    @PostMapping("/product/create")
    public ResponseModel createProduct(@Valid@RequestBody Product product) {
        ResponseModel response = new ResponseModel();
        try {
            productService.createProduct(product);
        } catch (Exception e) {
            response.message = "Product Creation Failed";
            response.responseCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            return response;
        }
        response.message = "Product Created";
        response.responseCode = HttpStatus.CREATED.value();
        return response;
    }

    @PutMapping("/product/update/{productId}")
    public ResponseModel updateProduct(@RequestBody Product product ,@PathVariable int productId) {
        ResponseModel response = new ResponseModel();
        try {
            productService.updateProduct(product , productId);
        } catch (Exception e) {
            response.message = "Product updation Failed";
            response.responseCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            return response;
        }
        response.message = "Product Updated";
        response.responseCode = HttpStatus.OK.value();
        return response;
    }

}
