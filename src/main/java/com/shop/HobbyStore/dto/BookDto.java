package com.shop.HobbyStore.dto;

import lombok.Data;

@Data
public class BookDto extends ProductDto{
    private String authorName;
    private String isbnNumber;
}
