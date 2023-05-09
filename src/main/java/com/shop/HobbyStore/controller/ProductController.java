package com.shop.HobbyStore.controller;

import com.shop.HobbyStore.entities.Product;
import com.shop.HobbyStore.service.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/products")
    public List<Product> findAllProducts()  {
        return productService.findAllProducts();
    }
}
