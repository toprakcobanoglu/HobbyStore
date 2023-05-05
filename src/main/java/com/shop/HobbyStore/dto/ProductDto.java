package com.shop.HobbyStore.dto;

import lombok.Data;

@Data
public class ProductDto {
    private int productId;
    private String name;
    private String genre;
    private String releaseDate;
    private double basePrice;
}
