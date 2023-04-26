package com.shop.HobbyStore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
@DiscriminatorValue(value = "book")
public class Book extends Product {
    @Column(name = "authorName")
    private String authorName;
    @Column(name = "isbnNumber")
    private String isbnNumber;

    @Override
    public double getFinalPrice() {
        return getBasePrice() + 2;
    }
}
