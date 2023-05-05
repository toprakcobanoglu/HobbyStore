package com.shop.HobbyStore.repository;

import com.shop.HobbyStore.entities.model.MusicAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicAlbumRepository extends JpaRepository<MusicAlbum, Integer> {
}
