package com.shop.HobbyStore.controller;

import com.shop.HobbyStore.entities.MusicAlbum;
import com.shop.HobbyStore.service.services.MusicAlbumService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musicAlbums")
public class MusicAlbumController {
    private final MusicAlbumService musicAlbumService;

    public MusicAlbumController(MusicAlbumService musicAlbumService) {
        this.musicAlbumService = musicAlbumService;
    }

    @GetMapping
    public List<MusicAlbum> findAllMusicAlbum() {
        return musicAlbumService.findAllMusicAlbum();
    }
    @GetMapping("{id}")
    public MusicAlbum findMusicAlbumById(@PathVariable("id")int id) {
        return musicAlbumService.findMusicAlbumById(id);
    }
    @PostMapping
    public MusicAlbum saveMusicAlbum(@RequestBody MusicAlbum musicAlbum)    {
        return musicAlbumService.saveMusicAlbum(musicAlbum);
    }
    @PutMapping("{id}")
    public MusicAlbum updateMusicAlbum(@RequestBody MusicAlbum musicAlbum)  {
        return musicAlbumService.saveMusicAlbum(musicAlbum);
    }
    @DeleteMapping("{id}")
    void deleteMusicAlbum(@PathVariable("id")int id)    {
        musicAlbumService.deleteMusicAlbum(id);
    }

}
