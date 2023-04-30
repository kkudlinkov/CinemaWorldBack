package ru.kkudlinkov.cinemaworldback.Controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kkudlinkov.cinemaworldback.Service.FilmService;
import org.springframework.ui.Model;

@Controller
@AllArgsConstructor
public class HomeController {
    private final FilmService filmService;
//    private final AuthService authService;


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("films", filmService.getAllFilms());

        model.addAttribute("isAuth", false);
        return "index";
    }
}
