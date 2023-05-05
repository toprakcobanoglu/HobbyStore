package com.shop.HobbyStore.service.services;

import com.shop.HobbyStore.entities.model.MusicAlbum;

import java.util.List;

public interface MusicAlbumService {
    List<MusicAlbum> findAllMusicAlbum();
    MusicAlbum findMusicAlbumById(int id);
    MusicAlbum saveMusicAlbum(MusicAlbum musicAlbum);
    MusicAlbum updateMusicAlbum(MusicAlbum musicAlbum);
    void deleteMusicAlbum(int id);
}
