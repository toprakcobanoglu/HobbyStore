package com.shop.HobbyStore.service.concretes;

import com.shop.HobbyStore.entities.model.Film;
import com.shop.HobbyStore.repository.FilmRepository;
import com.shop.HobbyStore.service.services.FilmService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> findAllFilm() {
        return filmRepository.findAll();
    }

    @Override
    public Film findFilmById(int id) {
        return filmRepository.findById(id).orElse(null);
    }

    @Override
    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }

    @Override
    public Film updateFilm(Film film) {
        return filmRepository.save(film);
    }

    @Override
    public void deleteFilm(int id) {
        filmRepository.deleteById(id);
    }
}
