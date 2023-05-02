package ru.kkudlinkov.cinemaworldback.Controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/add-to-favorite/{id}")
    public String addToFavorite(@PathVariable Integer id, Model model) {
        model.addAttribute("userInfo", authService.getUserInfo());

        if (userService.addToCartByProductId(id)) {
            return "redirect:/films/" + id + "?success";
        } else {
            return "redirect:/films/" + id + "?error";
        }
    }

    /**
     * Удаление из избранного
     *
     * @return
     */
    @PostMapping("/remove-from-cart/{id}")
    public String removeFromCart(Model model, @PathVariable int id) {
        model.addAttribute("userInfo", authService.getUserInfo());

        if (userService.removeFromCartByProductId(id)) {
            return "redirect:/user/profile?success";
        } else {
            return "redirect:/user/profile?error";
        }
    }
}
