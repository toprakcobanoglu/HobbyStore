package com.shop.HobbyStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDto extends ProductDto {
    private String directorName;
    private double imdbRate;
}
