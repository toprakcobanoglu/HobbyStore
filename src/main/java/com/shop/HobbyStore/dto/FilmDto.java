package com.shop.HobbyStore.dto;

import lombok.Data;

@Data
public class FilmDto extends ProductDto{
    private String directorName;
    private double imdbRate;
}
