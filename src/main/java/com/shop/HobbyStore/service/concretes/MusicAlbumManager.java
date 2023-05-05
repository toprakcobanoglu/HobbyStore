package com.shop.HobbyStore.service.concretes;

import com.shop.HobbyStore.entities.model.MusicAlbum;
import com.shop.HobbyStore.repository.MusicAlbumRepository;
import com.shop.HobbyStore.service.services.MusicAlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicAlbumManager implements MusicAlbumService {
    private final MusicAlbumRepository musicAlbumRepository;

    public MusicAlbumManager(MusicAlbumRepository musicAlbumRepository) {
        this.musicAlbumRepository = musicAlbumRepository;
    }

    @Override
    public List<MusicAlbum> findAllMusicAlbum() {
        return musicAlbumRepository.findAll();
    }

    @Override
    public MusicAlbum findMusicAlbumById(int id) {
        return musicAlbumRepository.findById(id).orElse(null);
    }

    @Override
    public MusicAlbum saveMusicAlbum(MusicAlbum musicAlbum) {
        return musicAlbumRepository.save(musicAlbum);
    }

    @Override
    public MusicAlbum updateMusicAlbum(MusicAlbum musicAlbum) {
        return musicAlbumRepository.save(musicAlbum);
    }

    @Override
    public void deleteMusicAlbum(int id) {
        musicAlbumRepository.deleteById(id);
    }
}
