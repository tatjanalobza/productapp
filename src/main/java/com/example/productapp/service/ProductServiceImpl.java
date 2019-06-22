package com.example.productapp.service;

import com.example.productapp.dao.ProductDAO;
import com.example.productapp.model.Product;
import com.example.productapp.model.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<Product> getProducts() {
        return productDAO.getProducts();
    }

    @Override
    public Product addProduct(ProductRequest productRequest) {
        return productDAO.addProduct(productRequest);
    }
}
