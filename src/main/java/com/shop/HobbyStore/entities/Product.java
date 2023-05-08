package com.shop.HobbyStore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private int productId;
    @Column(name = "name")
    private String name;
    @Column(name = "genre")
    private String genre;
    @Column(name = "releaseDate")
    private String releaseDate;
    @Column(name = "basePrice")
    private double basePrice;
    @Column(name = "earlyBirdPrice")
    private double earlyBirdPrice;

    public double getFinalPrice() {
        return basePrice;
    }

    @Transient
    public String getDiscriminatorType()    {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

    public abstract double getPureProfit();

    //public abstract double getPureProfit();
}
