package com.shop.HobbyStore.controller;

import com.shop.HobbyStore.entities.Sale;
import com.shop.HobbyStore.service.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }
    @GetMapping
    public List<Sale> findAllSales()   {
        return saleService.findAllSales();
    }
    @GetMapping("{id}")
    public Sale findSalesById(@PathVariable("id") int id)  {
        return saleService.findSaleById(id);
    }
    @DeleteMapping("{id}")
    void deleteSale(@PathVariable("id") int id) {
        saleService.deleteSale(id);
    }

}
