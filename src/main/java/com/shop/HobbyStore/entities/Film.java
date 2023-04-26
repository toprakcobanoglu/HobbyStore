package com.shop.HobbyStore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "films")
@DiscriminatorValue(value = "film")
public class Film extends Product{
    @Column(name = "directorName")
    private String directorName;
    @Column(name = "imdbRate")
    private double imdbRate;

    @Override
    public double getFinalPrice() {
        return getBasePrice() * 1.15;
    }
}
