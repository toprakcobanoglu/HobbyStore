package com.shop.HobbyStore.controller;

import com.shop.HobbyStore.entities.model.Book;
import com.shop.HobbyStore.entities.model.MusicAlbum;
import com.shop.HobbyStore.service.services.MusicAlbumService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/musicAlbums")
public class MusicAlbumController {
    private final MusicAlbumService musicAlbumService;

    public MusicAlbumController(MusicAlbumService musicAlbumService) {
        this.musicAlbumService = musicAlbumService;
    }

    @GetMapping
    public ModelAndView findAllMusicAlbums(Model model) {
        List<MusicAlbum> musicAlbums = musicAlbumService.findAllMusicAlbum();
        model.addAttribute("musicAlbums" , musicAlbums);
        return new ModelAndView("album-list");
    }

    @GetMapping("{id}")
    public MusicAlbum findMusicAlbumById(@PathVariable("id")int id) {
        return musicAlbumService.findMusicAlbumById(id);
    }
    @PostMapping("/saveMusicAlbum")
    public MusicAlbum saveMusicAlbum(@RequestBody MusicAlbum musicAlbum)    {
        return musicAlbumService.saveMusicAlbum(musicAlbum);
    }
    @PutMapping("/editMusicAlbum/{albumId}")
    public MusicAlbum updateMusicAlbum(@PathVariable("albumId") int albumId, @RequestBody MusicAlbum musicAlbum) {
        MusicAlbum existingAlbum = musicAlbumService.findMusicAlbumById(albumId);
        if (existingAlbum != null) {
            existingAlbum.setName(musicAlbum.getName());
            existingAlbum.setGenre(musicAlbum.getGenre());
            existingAlbum.setReleaseDate(musicAlbum.getReleaseDate());
            existingAlbum.setBasePrice(musicAlbum.getBasePrice());
            existingAlbum.setSingerName(musicAlbum.getSingerName());
            existingAlbum.setNumberOfSongs(musicAlbum.getNumberOfSongs());

            return musicAlbumService.saveMusicAlbum(existingAlbum);
        } else {
            throw new IllegalArgumentException("Album not found with id: " + albumId);
        }
    }
    @DeleteMapping("deleteMusicAlbum/{id}")
    void deleteMusicAlbum(@PathVariable("id")int id)    {
        musicAlbumService.deleteMusicAlbum(id);
    }
}
