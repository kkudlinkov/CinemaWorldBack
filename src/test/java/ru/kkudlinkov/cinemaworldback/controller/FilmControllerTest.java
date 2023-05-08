package ru.kkudlinkov.cinemaworldback.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import ru.kkudlinkov.cinemaworldback.Controller.FilmController;
import ru.kkudlinkov.cinemaworldback.Controller.HomeController;
import ru.kkudlinkov.cinemaworldback.Domain.model.Film;
import ru.kkudlinkov.cinemaworldback.Service.AuthService;
import ru.kkudlinkov.cinemaworldback.Service.FilmService;
import ru.kkudlinkov.cinemaworldback.Service.UserService;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class FilmControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FilmService filmService;

    @Mock
    private UserService userService;

    @Mock
    private AuthService authService;

    @Mock
    private Model model;

    @InjectMocks
    private FilmController filmController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(filmController).build();
    }

    @Test
    public void getProductByIdTest() throws Exception {

        Film product = new Film();
        product.setId(1);
        product.setName("Test");

        when(filmService.getById(1)).thenReturn(product);

        mockMvc
                .perform(get("/films/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("film"));
    }

}
