package com.example.productapp.service;

import com.example.productapp.model.Product;
import com.example.productapp.model.ProductRequest;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product addProduct(ProductRequest productRequest);

}
