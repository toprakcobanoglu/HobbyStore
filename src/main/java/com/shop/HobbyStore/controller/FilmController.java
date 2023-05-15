package com.shop.HobbyStore.controller;

import com.shop.HobbyStore.entities.model.Book;
import com.shop.HobbyStore.entities.model.Film;
import com.shop.HobbyStore.service.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    @Autowired
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
    @PostMapping("/saveFilm")
    public Film saveFilm(@RequestBody Film film)    {
        return filmService.saveFilm(film);
    }
    @PutMapping("/editFilm/{filmId}")
    public Film updateFilm(@PathVariable("filmId") int filmId, @RequestBody Film film) {
        Film existingFilm = filmService.findFilmById(filmId);
        if (existingFilm != null) {
            existingFilm.setName(film.getName());
            existingFilm.setGenre(film.getGenre());
            existingFilm.setReleaseDate(film.getReleaseDate());
            existingFilm.setBasePrice(film.getBasePrice());
            existingFilm.setDirectorName(film.getDirectorName());
            existingFilm.setImdbRate(film.getImdbRate());

            return filmService.saveFilm(existingFilm);
        } else {
            throw new IllegalArgumentException("Book not found with id: " + filmId);
        }
    }
    @DeleteMapping("deleteFilm/{id}")
    void deleteFilm(@PathVariable("id")int id)  {
        filmService.deleteFilm(id);
    }
}
