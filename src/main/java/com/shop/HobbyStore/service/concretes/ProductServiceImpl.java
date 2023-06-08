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
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;


    public ProductServiceImpl(ProductRepository productRepository, SaleRepository saleRepository) {
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

        List<Integer> purchasedBookIds = books.stream().map(Product::getProductId).toList();

        double totalPrice = findTotalAmount(products, purchasedProductMap);
        double sumEarlyBirdPrice = sumEarlyBirdPrice(products);

        int productSize = purchasedProducts.stream().mapToInt(PurchasedProduct::getCount).sum();
        int bookSize = purchasedProducts.stream().filter(pp -> purchasedBookIds.contains(pp.getProductId())).mapToInt(PurchasedProduct::getCount).sum();

        //Kampanya uygulanmasi icin sepetteki urun sayisinin 2 veya 2 den fazla olmasi gerekir
        double twoItemCampaignDiscountAmount = productSize >= 2 ? calculateTwoItemsDiscount(products) : 0d;
        double twoBooksCampaignDiscountAmount = bookSize >= 2 ? calculateTwoBooksDiscount(books) : 0d;

        double finalTotalPrice = (totalPrice - Math.max(twoBooksCampaignDiscountAmount, twoItemCampaignDiscountAmount));
        double pureProfit = finalTotalPrice - sumEarlyBirdPrice;

        //Id ve count bilgisi girilerek request atildiginda "Sale" tablosuna satislar kaydedilir
        //Body : "productId" ve "count"
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


    //Sepete eklenen urunlerin arasindaki turu kitap olanlari bulma
    private List<Product> findPurchasedBooks(List<Product> products) {
        return products.stream()
                .filter(product -> product.getDiscriminatorType().equalsIgnoreCase("Book"))
                .collect(Collectors.toList());
    }

    //Satislardan elde edilen kar bulmak icin, sepete eklenen urunlerin gelis fiyatlarini toplama
    private double sumEarlyBirdPrice(List<Product> products)    {
        return products.stream().mapToDouble(Product::getEarlyBirdPrice).sum();
    }

    //Sepetteki urunlerin toplam tutari
    private double findTotalAmount(List<Product> products, Map<Integer, PurchasedProduct> purchasedProductMap) {
        double sum = 0;
        for (Product product : products)    {
            int count = purchasedProductMap.get(product.getProductId()).getCount();
            sum += product.getFinalPrice() * count;
        }
        return sum;
    }

    //Urun kampanyasi hesaplama
    private double calculateTwoItemsDiscount(List<Product> products) {
        return findCheapestProductPrice(products) / 2;
    }

    //Kitap kampanyasi hesaplama
    private double calculateTwoBooksDiscount(List<Product> books) {
        return findCheapestProductPrice(books);
    }

    //Urunlerin arasindaki en ucuz fiyatli urunu bulma
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