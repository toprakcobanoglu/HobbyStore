package com.shop.HobbyStore.service.concretes;

import com.shop.HobbyStore.entities.Book;
import com.shop.HobbyStore.entities.Product;
import com.shop.HobbyStore.entities.PurchasedProduct;
import com.shop.HobbyStore.repository.ProductRepository;
import com.shop.HobbyStore.service.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductManager implements ProductService {
    private final ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
        List<Book> purchasedBooks = new ArrayList<>();

        double totalPrice = 0;
        int bookCount = 0;
        int itemCount = products.size();

        for (var product : products) {
            String productType = product.getDiscriminatorType();
            if (productType.equalsIgnoreCase("Book")) {
                purchasedBooks.add((Book) product);
                bookCount++;
            }

            int count = purchasedProductMap.get(product.getProductId()).getCount();
            totalPrice += product.getFinalPrice() * count;
        }

        if (itemCount >= 2) {
            Product lowestPriceProduct = products.get(0);
            double productDiscount = 0;
            for (Product product : products) {
                if (product.getFinalPrice() < lowestPriceProduct.getFinalPrice()) {
                    lowestPriceProduct = product;
                    productDiscount = (lowestPriceProduct.getFinalPrice() * 0.5);
                }
            }
            double totalProductPrice = totalPrice - productDiscount;
            double productDiscountRate = productDiscount / totalPrice;

            if (bookCount >= 2) {
                double bookDiscount = 0;
                Product lowestPriceBook = purchasedBooks.get(0);
                for (Book book : purchasedBooks) {
                    if (book.getFinalPrice() < lowestPriceBook.getFinalPrice()) {
                        lowestPriceBook = book;
                        bookDiscount = lowestPriceBook.getFinalPrice();
                    }
                }
                double totalBookPrice = totalPrice - bookDiscount;
                double bookDiscountRate = bookDiscount / totalPrice;

                if (bookDiscountRate > productDiscountRate) {
                    return "total book price: " + totalBookPrice;
                } else {
                    return "total product price: " + totalProductPrice;
                }
            }
            return "total product price: " + totalProductPrice;
        }
        return "total: " + totalPrice;
    }


    private Map<Integer, PurchasedProduct> getPurchasedProductMap(List<PurchasedProduct> purchasedProducts) {

        Map<Integer, PurchasedProduct> purchasedProductMap = new HashMap<>();

        if (purchasedProducts == null || purchasedProducts.isEmpty())   {
            return purchasedProductMap;
        }

        for (var product : purchasedProducts)   {
            if (product == null || purchasedProductMap.containsKey(product.getProductId())) {
                continue;
            }
            purchasedProductMap.put(product.getProductId(), product);
        }

        return purchasedProductMap;
    }

    private List<Integer> getProductIds(List<PurchasedProduct> purchasedProducts)   {
        List<Integer> productIds = new ArrayList<>();
        for (var purchasedProduct : purchasedProducts)  {
            productIds.add(purchasedProduct.getProductId());
        }
        return productIds;
    }

}