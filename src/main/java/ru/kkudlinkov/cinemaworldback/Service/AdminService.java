package ru.kkudlinkov.cinemaworldback.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kkudlinkov.cinemaworldback.Domain.model.Film;
import ru.kkudlinkov.cinemaworldback.Domain.model.User;
import ru.kkudlinkov.cinemaworldback.Mapper.UserMapper;


import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {
    private final UserService userService;
    private final FilmService filmService;
    private final UserMapper userMapper;

    /**
     * Получение всех пользователей
     */
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Получение пользователя по id
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public User getUserById(int id) {
        return userService.getById(id);
    }


    /**
     * Получение всех Фильмов
     *
     * @return
     */
    public List<Film> getAllProducts() {
        return filmService.getAllFilms();
    }


    /**
     * Получение фильма по id
     *
     * @param id
     * @return
     */
    public Film getFilmById(int id) {
        return filmService.getById(id);
    }


    /**
     * Сохранение фильма
     *
     * @param film
     */
    @Transactional
    public void saveFilm(Film film) {
        filmService.save(film);
    }
}
