package ru.kkudlinkov.cinemaworldback.Service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kkudlinkov.cinemaworldback.Domain.dto.UserRegisterDTO;
import ru.kkudlinkov.cinemaworldback.Domain.model.Film;
import ru.kkudlinkov.cinemaworldback.Domain.model.User;
import ru.kkudlinkov.cinemaworldback.Mapper.UserMapper;
import ru.kkudlinkov.cinemaworldback.Repository.UserRepository;

import java.security.PrivateKey;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthService authService;
    private final FilmService filmService;

    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * Поиск пользователя по email
     *
     * @param username
     * @return
     */
    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Получение конкретного юзера
     *
     * @return
     */
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    /**
     * Получение конкретного юзера
     *
     * @return
     */
    public User getById(int id) {
        return findById(id).orElseThrow();
    }


    /**
     * Регистрация пользователя
     *
     * @param userRegisterDTO
     */
    public void register(UserRegisterDTO userRegisterDTO) {
        save(userMapper.registerDTOToUser(userRegisterDTO));
    }


    /**
     * Добавление продукта в корзину
     *
     * @param film
     */
    public boolean addToCart(Film film) {
        // Получаем авторизованного пользователя
        var user = authService.getAuthUser().orElse(null);

        // Если пользователь не авторизован, то ничего не делаем
        if (user == null) {
            return false;
        }

        // Добавляем товар в корзину
        var films = user.getFilms();

        // Если товар уже есть в корзине, то ничего не делаем
        if (films.contains(film)) {
            return false;
        }

        // Добавляем товар в корзину
        films.add(film);

        // Сохраняем пользователя
        save(user);
        return true;
    }

    /**
     * Удаление продукта из корзины
     *
     * @param film
     * @return
     */
    public boolean removeFromCart(Film film) {
        // Получаем авторизованного пользователя
        var user = authService.getAuthUser().orElse(null);

        // Если пользователь не авторизован, то ничего не делаем
        if (user == null) {
            return false;
        }

        // Удаляем товар из корзины
        var films = user.getFilms();
        films.remove(film);

        // Сохраняем пользователя
        save(user);
        return true;
    }

    /**
     * Добавление товара в корзину по id товара
     *
     * @param filmId
     * @return
     */
    public boolean addToCartByProductId(int filmId) {
        // Получаем авторизованного пользователя
        var user = authService.getAuthUser().orElse(null);

        // Если пользователь не авторизован, то ничего не делаем
        if (user == null) {
            return false;
        }

        // Получаем товар по id
        var film = filmService.findById(filmId).orElse(null);

        // Получаем список товаров в корзине
        var films = user.getFilms();

        // Если товар уже есть в корзине, то ничего не делаем
        if (films.contains(film)) {
            return false;
        }

        // Добавляем товар в корзину
        films.add(film);

        // Сохраняем пользователя
        save(user);
        return true;
    }

    /**
     * Удаление товара из корзины по id товара
     *
     * @param filmId
     * @return
     */
    public boolean removeFromCartByProductId(int filmId) {
        var product = filmService.findById(filmId).orElse(null);

        if (product == null) {
            return false;
        } else {
            return removeFromCart(product);
        }
    }
}
