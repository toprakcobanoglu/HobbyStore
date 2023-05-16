package com.shop.HobbyStore.service.concretes;

import com.shop.HobbyStore.entities.Sale;
import com.shop.HobbyStore.repository.SaleRepository;
import com.shop.HobbyStore.service.services.SaleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<Sale> findAllSales() {
        return saleRepository.findAll();
    }

    @Override
    public Sale findSaleById(int id) {
        return saleRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteSale(int id) {
        saleRepository.deleteById(id);
    }
}
