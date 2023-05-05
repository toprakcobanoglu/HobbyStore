package com.shop.HobbyStore.service.concretes;

import com.shop.HobbyStore.entities.Product;
import com.shop.HobbyStore.entities.PurchasedProduct;
import com.shop.HobbyStore.entities.Sale;
import com.shop.HobbyStore.repository.ProductRepository;
import com.shop.HobbyStore.repository.SaleRepository;
import com.shop.HobbyStore.service.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductManager implements ProductService {
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;


    public ProductManager(ProductRepository productRepository, SaleRepository saleRepository) {
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllById(List<PurchasedProduct> purchasedProducts) {
        List<Integer> productIds = getProductIds(purchasedProducts);
        return productRepository.findAllById(productIds);
    }

    @Override
    public String findTotalPrice(List<PurchasedProduct> purchasedProducts) {
        Map<Integer, PurchasedProduct> purchasedProductMap = getPurchasedProductMap(purchasedProducts);
        if (purchasedProductMap.isEmpty()) {
            return "total price: " + 0;
        }

        List<Integer> productIds = new ArrayList<>(purchasedProductMap.keySet());
        List<Product> products = productRepository.findAllById(productIds);
        List<Product> books = findPurchasedBooks(products);

        double totalPrice = findTotalAmount(products);
        double sumEarlyBirdPrice = sumEarlyBirdPrice(products);

        double twoItemCampaignDiscountAmount = products.size() >= 2 ? calculateTwoItemsDiscount(products) : 0d;
        double twoBooksCampaignDiscountAmount = books.size() >= 2 ? calculateTwoBooksDiscount(books) : 0d;

        double finalTotalPrice = (totalPrice - Math.max(twoBooksCampaignDiscountAmount, twoItemCampaignDiscountAmount));
        double pureProfit = finalTotalPrice - sumEarlyBirdPrice;

        Sale sale = new Sale();
        sale.setFinalTotalPrice(finalTotalPrice);
        sale.setPureProfit(pureProfit);
        saleRepository.save(sale);

        StringBuilder stringBuilder = new StringBuilder();

        for (Product product : products)    {
            stringBuilder.append("Product Name --> ").append(product.getName())
                    .append(" : ").append(product.getFinalPrice()).append("\n");
        }
        stringBuilder.append("Total : ").append(finalTotalPrice).append("\n");
        return stringBuilder.toString();
    }


    private List<Product> findPurchasedBooks(List<Product> products) {
        return products.stream()
                .filter(product -> product.getDiscriminatorType().equalsIgnoreCase("Book"))
                .collect(Collectors.toList());
    }

    private double sumEarlyBirdPrice(List<Product> products)    {
        return products.stream().mapToDouble(Product::getEarlyBirdPrice).sum();
    }

    private double findTotalAmount(List<Product> products) {
        return products.stream().mapToDouble(Product::getFinalPrice).sum();
    }

    private double calculateTwoItemsDiscount(List<Product> products) {
        return findCheapestProductPrice(products) / 2;
    }

    private double calculateTwoBooksDiscount(List<Product> books) {
        return findCheapestProductPrice(books);
    }

    private double findCheapestProductPrice(List<Product> products) {
        return products.stream()
                .min(Comparator.comparingDouble(Product::getFinalPrice))
                .map(Product::getFinalPrice)
                .orElse(0d);
    }


    private Map<Integer, PurchasedProduct> getPurchasedProductMap(List<PurchasedProduct> purchasedProducts) {

        Map<Integer, PurchasedProduct> purchasedProductMap = new HashMap<>();

        if (purchasedProducts == null || purchasedProducts.isEmpty()) {
            return purchasedProductMap;
        }

        for (var product : purchasedProducts) {
            if (product == null || purchasedProductMap.containsKey(product.getProductId())) {
                continue;
            }
            purchasedProductMap.put(product.getProductId(), product);
        }

        return purchasedProductMap;
    }


    private List<Integer> getProductIds(List<PurchasedProduct> purchasedProducts) {
        List<Integer> productIds = new ArrayList<>();
        for (var purchasedProduct : purchasedProducts) {
            productIds.add(purchasedProduct.getProductId());
        }
        return productIds;
    }

}