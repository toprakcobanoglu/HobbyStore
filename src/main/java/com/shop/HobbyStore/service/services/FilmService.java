package com.shop.HobbyStore.service.services;

import com.shop.HobbyStore.entities.Film;

import java.util.List;

public interface FilmService {
    List<Film> findAllFilm();
    Film findFilmById(int id);
    Film saveFilm(Film film);
    Film updateFilm(Film film);
    void deleteFilm(int id);
}
