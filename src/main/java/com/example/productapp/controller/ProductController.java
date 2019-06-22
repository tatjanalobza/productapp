package com.example.productapp.controller;

import com.example.productapp.model.Product;
import com.example.productapp.model.ProductRequest;
import com.example.productapp.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@Api(value = "productapp", description = "application for adding and viewing products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Lists all available products")
    @GetMapping(value = "/product", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to list products"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")})
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @ApiOperation(value = "Adds a product")
    @PostMapping(value = "/product", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added the product"),
            @ApiResponse(code = 400, message = "The provided parameter is invalid"),
            @ApiResponse(code = 401, message = "You are not authorized to add products"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")})
    public Product addProduct(@Valid @RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }
}
