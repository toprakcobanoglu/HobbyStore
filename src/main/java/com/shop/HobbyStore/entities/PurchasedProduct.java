package com.shop.HobbyStore.entities;

import jakarta.persistence.Transient;
import lombok.Data;

@Data
public class PurchasedProduct {
    @Transient
    private int productId;
    @Transient
    private int count;
}
