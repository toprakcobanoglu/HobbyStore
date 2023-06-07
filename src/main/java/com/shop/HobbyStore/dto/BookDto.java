package com.shop.HobbyStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto extends ProductDto {
    private String authorName;
    private String isbnNumber;
}
