package com.shop.HobbyStore.controller;

import com.shop.HobbyStore.entities.Film;
import com.shop.HobbyStore.service.services.FilmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<Film> findAllFilm() {
        return filmService.findAllFilm();
    }
    @GetMapping("{id}")
    public Film findFilmById(@PathVariable("id")int id) {
        return filmService.findFilmById(id);
    }
    @PostMapping
    public Film saveFilm(@RequestBody Film film)    {
        return filmService.saveFilm(film);
    }
    @PutMapping("{id}")
    public Film updateFilm(@RequestBody Film film)   {
        return filmService.saveFilm(film);
    }
    @DeleteMapping("{id}")
    void deleteFilm(@PathVariable("id")int id)  {
        filmService.deleteFilm(id);
    }
}
