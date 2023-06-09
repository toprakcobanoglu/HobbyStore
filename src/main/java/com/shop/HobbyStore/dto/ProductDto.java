package com.shop.HobbyStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private int productId;
    private String name;
    private String genre;
    private String releaseDate;
    private double basePrice;
}
