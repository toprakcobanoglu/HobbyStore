package com.shop.HobbyStore.repository;

import com.shop.HobbyStore.entities.Book;
import com.shop.HobbyStore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
