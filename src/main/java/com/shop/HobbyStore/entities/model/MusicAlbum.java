package com.shop.HobbyStore.entities.model;

import com.shop.HobbyStore.entities.Product;
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
@Table(name = "musicAlbums")
@DiscriminatorValue(value = "musicAlbum")
public class MusicAlbum extends Product {
    @Column(name = "singerName")
    private String singerName;
    @Column(name = "numberOfSongs")
    private int numberOfSongs;

    @Override
    public double getFinalPrice() {
        return getBasePrice() * 0.95;
    }
    @Override
    public double getPureProfit()   {
        return getFinalPrice() - getEarlyBirdPrice();
    }
}
