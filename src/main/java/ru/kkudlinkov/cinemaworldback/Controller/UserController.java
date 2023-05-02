package ru.kkudlinkov.cinemaworldback.Controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import ru.kkudlinkov.cinemaworldback.Service.AuthService;
import ru.kkudlinkov.cinemaworldback.Service.FilmService;
import ru.kkudlinkov.cinemaworldback.Service.UserService;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final FilmService filmService;
    private final AuthService authService;
    private final UserService userService;

    /**
     * Страница профиля пользователя
     *
     * @return
     */
    @GetMapping("/profile")
    public String profile(Model model) {
        // Провряем, авторизован ли пользователь добавляя переменную isAuth
        model.addAttribute("userInfo", authService.getUserInfo());

        // Если пользователь авторизован, то добавляем его в модель
        var user = authService.getAuthUser().orElse(null);

        var favourite = user.getFilms();
        model.addAttribute("favourite", favourite);
        model.addAttribute("favouriteTotal", FilmService.getFavouritesTotal(favourite));

        return "profile";
    }


    @PostMapping("/add-to-favourite/{id}")
    public String addToFavorite(Model model, @PathVariable int id) {
        model.addAttribute("userInfo", authService.getUserInfo());
        userService.addToFavouriteById(id);
        return "redirect:/";

    }

    /*
     * Удаление товара из избранного по id товара
     */
    @PostMapping("/delete-from-favourite/{id}")
    public String deleteFromFavourite(Model model, @PathVariable int id) {
        model.addAttribute("userInfo", authService.getUserInfo());
        userService.deleteFromFavouriteById(id);
        return "redirect:/user/profile";
    }
}
