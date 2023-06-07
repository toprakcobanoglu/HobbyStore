package com.shop.HobbyStore.service.services;

import com.shop.HobbyStore.entities.Product;
import com.shop.HobbyStore.entities.PurchasedProduct;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();
    List<Product> findAllById(List<PurchasedProduct> purchasedProducts);
    String findTotalPrice(List<PurchasedProduct> purchasedProducts);
}
