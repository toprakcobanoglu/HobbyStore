package com.shop.HobbyStore.controller;

import com.shop.HobbyStore.entities.model.Film;
import com.shop.HobbyStore.service.services.FilmService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public ModelAndView findAllBooks(Model model)   {
        List<Film> films = filmService.findAllFilm();
        model.addAttribute("films" , films);
        return new ModelAndView("film-list");
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
