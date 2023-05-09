package ru.kkudlinkov.cinemaworldback.Service;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.kkudlinkov.cinemaworldback.Domain.model.Film;
import ru.kkudlinkov.cinemaworldback.Repository.FilmRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        return filmRepository.findAll(Sort.by(Sort.Direction.ASC, "id")); // Сортировка по ID
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


    /**
     * Получение списка избранных фильмов
     *
     * @return
     */
    public static double getFavouritesTotal(Set<Film> films) {
        // Возврат списка избранных фильмов
        var total = 0;
        for (Film film : films) {
            total += film.getId();
        }
        return total;
    }

    public Film save(Film film) {
        return filmRepository.save(film);
    }

    public void delete(int filmId) {
        filmRepository.deleteById(filmId);
    }
}
