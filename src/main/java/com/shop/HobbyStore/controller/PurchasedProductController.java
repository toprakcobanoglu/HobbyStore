package com.shop.HobbyStore.controller;

import com.shop.HobbyStore.entities.Product;
import com.shop.HobbyStore.entities.PurchasedProduct;
import com.shop.HobbyStore.entities.Sale;
import com.shop.HobbyStore.service.services.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/purchase")
public class PurchasedProductController {

    private final ProductService productService;

    public PurchasedProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public List<Product> purchasedProduct(@RequestBody List<PurchasedProduct> purchasedProducts) {
        return productService.findAllById(purchasedProducts);
    }

    @PostMapping("/total")
    public String purchasedProductTotal(@RequestBody List<PurchasedProduct> purchasedProducts) {
        return productService.findTotalPrice(purchasedProducts);
    }
}
