package ru.kkudlinkov.cinemaworldback.Controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kkudlinkov.cinemaworldback.Domain.model.Film;
import ru.kkudlinkov.cinemaworldback.Service.AdminService;


@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;


    /**
     * Страница пользователей
     *
     * @param model
     * @return
     */
    @GetMapping("/users")
    public String users(
            Model model
    ) {
        model.addAttribute("users", adminService.getAllUsers());
        return "admin/users";
    }

    /**
     * Страница со списом товаров
     *
     * @param model
     */
    @GetMapping("/films")
    public String products(
            Model model
    ) {
        model.addAttribute("films", adminService.getAllProducts());
        return "admin/films";
    }

    /**
     * Редактирование товара
     *
     * @param id
     */
    @GetMapping("/films/edit/{id}")
    public String editProduct(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("film", adminService.getFilmById(id));
        return "admin/edit-film";
    }

    /**
     * Редактирование товара
     */
    @PostMapping("/films/edit")
    public String editProductPost(
            @ModelAttribute("film") Film film
            ) {
        adminService.saveFilm(film);
        return "redirect:/admin/films?success";
    }

    /**
     * Добавление товара
     *
     * @return
     */
    @GetMapping("/films/add")
    public String addProduct(
            Model model
    ) {
        model.addAttribute("film", new Film());
        return "admin/add-film";
    }

    /**
     * Добавление товара
     *
     * @return
     */
    @PostMapping("/films/add")
    public String addProductPost(
            @ModelAttribute("film") Film film
    ) {
        adminService.saveFilm(film);
        return "redirect:/admin/films?success";
    }

}

