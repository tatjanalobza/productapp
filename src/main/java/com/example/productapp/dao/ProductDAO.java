package com.example.productapp.dao;

import com.example.productapp.model.Product;
import com.example.productapp.model.ProductRequest;

import java.util.List;

public interface ProductDAO {

    List<Product> getProducts();

    Product addProduct(ProductRequest productRequest);

}
