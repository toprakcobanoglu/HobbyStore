package com.shop.HobbyStore.controller;

import com.shop.HobbyStore.entities.Product;
import com.shop.HobbyStore.service.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAllProducts()  {
        return productService.findAllProducts();
    }


    /* @GetMapping
    public ModelAndView findAllProducts() {
        ModelAndView modelAndView = new ModelAndView("product-list");
        List<Product> products = productService.findAllProducts();
        modelAndView.addObject("products", products);
        return modelAndView;
    }   */

}
