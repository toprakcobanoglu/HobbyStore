package com.shop.HobbyStore.service.services;

import com.shop.HobbyStore.entities.Sale;

import java.util.List;

public interface SaleService {
    List<Sale> findAllSales();
    Sale findSaleById(int id);
    void deleteSale(int id);
}
