package ru.kkudlinkov.cinemaworldback.Controller;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kkudlinkov.cinemaworldback.Service.AuthService;
import ru.kkudlinkov.cinemaworldback.Service.FilmService;

@Controller
@AllArgsConstructor
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;
    private final AuthService authService;

    /**
     * Получение конкретного фильма
     * @return
     */
    @GetMapping("/{id}")
    public String getFilm(
            @PathVariable("id") int id,
            Model model
    ) {
        // Провряем, авторизован ли пользователь добавляя переменную
        model.addAttribute("userInfo", authService.getUserInfo());

        // Проверка на существование продукта
        var film = filmService.getById(id);
        if (film == null) {
            return "redirect:/error";
        }
        model.addAttribute("film", film);
        return "film";
    }
}
