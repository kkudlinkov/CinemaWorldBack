package ru.kkudlinkov.cinemaworldback.Service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kkudlinkov.cinemaworldback.Domain.model.Film;
import ru.kkudlinkov.cinemaworldback.Repository.FilmRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;

    /**
     * Получение всех фильмов
     *
     * @return
     */
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    /**
     * Получение конкретного фильма
     *
     * @return
     */
    public Optional<Film> findById(int id) {
        return filmRepository.findById(id);
    }

    /**
     * Получение конкретного фильма
     *
     * @return
     */
    public Film getById(int id) {
        return findById(id).orElseThrow();
    }
}