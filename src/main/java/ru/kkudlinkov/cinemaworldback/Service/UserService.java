package ru.kkudlinkov.cinemaworldback.Service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kkudlinkov.cinemaworldback.Domain.dto.UserEditDTO;
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
     * Поиск пользователя по username
     *
     * @param username
     * @return
     */
    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Поиск пользователя по email
     *
     * @param email
     * @return
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
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
     * Регистрация пользователя
     *
     * @param userRegisterDTO
     */
    public void register(UserRegisterDTO userRegisterDTO) {
        save(userMapper.registerDTOToUser(userRegisterDTO));
    }

    /**
     * Добавление товара в избранное по id фильма
     *
     * @param filmId
     * @return
     */
    public boolean addToFavouriteById(int filmId) {
        //Получаем авторизованного пользователя
        var user = authService.getAuthUser().orElse(null);

        // Если пользователь не авторизован, то ничего не делаем
        if (user == null) {
            return false;
        }

        // Добавляем фильм в избранное
        var film = filmService.findById(filmId).orElse(null);
        // Получаем список фильмов пользователя
        var films = user.getFilms();

        // Если фильм уже есть в избранном, то ничего не делаем
        if (films.contains(film)) {
            return false;
        }
        //Добавляем фильм в избранное
        films.add(film);
        //Сохраняем пользователя
        save(user);
        return true;
    }


    public boolean deleteFromFavouriteById(int id) {
        var film = filmService.findById(id).orElse(null);

        if (film == null) {
            return false;
        } else {
            return removeFromFavourite(film);
        }
    }

    private boolean removeFromFavourite(Film film) {
        var user = authService.getAuthUser().orElse(null);
        if (user == null) {
            return false;
        }
        var films = user.getFilms();
        films.remove(film);
        save(user);
        return true;
    }

    /**
     * Обновление данных пользователя
     */

    public void update(User user, UserEditDTO userEditDTO) {
        user.setUsername(userEditDTO.getUsername());
        user.setImage(userEditDTO.getImage());
        user.setDescription(userEditDTO.getDescription());
        save(user);
    }

    public boolean update(UserEditDTO userEditDTO) {
        // Получаем авторизованного пользователя
        var user = authService.getAuthUser().orElse(null);

        // Если пользователь не авторизован, то ничего не делаем
        if (user == null) {
            return false;
        }
        // Проверяем, что username не занят другим пользователем
        var userWithSameUsername = findByUserName(userEditDTO.getUsername()).orElse(null);
        if (userWithSameUsername != null && !userWithSameUsername.equals(user)) {
            return false;
        }

        // Обновляем данные пользователя
        update(user, userEditDTO);
        return true;
    }

    public UserEditDTO getUserEditDTO() {
        var user = authService.getAuthUser().orElse(null);
        if (user == null) {
            return null;
        }
        var user1 = findByUserName(user.getUsername()).orElse(null);
        return userMapper.userToUserEditDTO(user1);
    }
}

