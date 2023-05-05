package com.shop.HobbyStore.controller;

import com.shop.HobbyStore.entities.Sale;
import com.shop.HobbyStore.service.services.SaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;


    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public List<Sale> findAllSales()    {
        return saleService.findAllSales();
    }
    @PostMapping
    public Sale saveSale(@RequestBody Sale sale)    {
        return saleService.saveSale(sale);
    }
}
