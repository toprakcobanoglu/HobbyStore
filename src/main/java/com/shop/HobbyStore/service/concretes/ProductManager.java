package com.shop.HobbyStore.service.concretes;

import com.shop.HobbyStore.entities.Book;
import com.shop.HobbyStore.entities.Product;
import com.shop.HobbyStore.entities.PurchasedProduct;
import com.shop.HobbyStore.repository.ProductRepository;
import com.shop.HobbyStore.service.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        List<Product> books = findPurchasedBooks(products);

        double totalPrice = findTotalAmount(products);

        double twoItemCampaignDiscountAmount = products.size() > 2 ? calculateTwoItemsDiscount(products) : 0d;
        double twoBooksCampaignDiscountAmount = books.size() > 2 ? calculateTwoBooksDiscount(books) : 0d;

        return "total   : " + (totalPrice - Math.max(twoBooksCampaignDiscountAmount, twoItemCampaignDiscountAmount));
    }

    private List<Product> findPurchasedBooks(List<Product> products) {
        return products.stream()
                .filter(product -> product.getDiscriminatorType().equalsIgnoreCase("Book"))
                .collect(Collectors.toList());
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

    private String discountedPrice(List<Product> products, List<Book> purchasedBooks, double totalPrice, int bookCount) {
        double productDiscount = findLowestPriceProduct(products).getFinalPrice();
        Product lowestPriceProduct = findLowestPriceProduct(products);
        findLowestPriceProduct(products);

        double totalProductPrice = totalPrice - productDiscount;
        double productDiscountRate = productDiscount / totalPrice;
        if (bookCount >= 2) {
            return calculateBookDiscount(purchasedBooks, totalPrice, totalProductPrice, productDiscountRate);
        }
        return "total product price: " + totalProductPrice;
    }

    private String calculateBookDiscount(List<Book> purchasedBooks, double totalPrice, double totalProductPrice, double productDiscountRate) {
        double bookDiscount = findLowestPriceBook(purchasedBooks).getFinalPrice();
        Book lowestPriceBook = findLowestPriceBook(purchasedBooks);

        double totalBookPrice = totalPrice - bookDiscount;
        double bookDiscountRate = bookDiscount / totalPrice;
        if (bookDiscountRate > productDiscountRate) {
            return "total book price: " + totalBookPrice;
        } else {
            return "total product price: " + totalProductPrice;
        }
    }


    private Book findLowestPriceBook(List<Book> purchasedBooks) {
        Book lowestPriceBook = purchasedBooks.get(0);
        for (Book book : purchasedBooks) {
            if (book.getFinalPrice() < lowestPriceBook.getFinalPrice()) {
                lowestPriceBook = book;
            }
        }
        return lowestPriceBook;
    }

    private Product findLowestPriceProduct(List<Product> products) {
        Product lowestPriceProduct = products.get(0);
        for (Product product : products) {
            if (product.getFinalPrice() < lowestPriceProduct.getFinalPrice()) {
                lowestPriceProduct = product;
            }
        }
        return lowestPriceProduct;
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