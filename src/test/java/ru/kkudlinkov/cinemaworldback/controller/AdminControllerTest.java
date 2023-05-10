package ru.kkudlinkov.cinemaworldback.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.kkudlinkov.cinemaworldback.Controller.AdminController;
import ru.kkudlinkov.cinemaworldback.Service.AdminService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminControllerTest.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new AdminController(adminService)).build();
    }


    @Test
    public void usersTest() throws Exception {
        mockMvc.perform(get("/admin/users")).andExpect(status().isOk()).andExpect(view().name("admin/users"));
    }

    @Test
    public void filmsTest() throws Exception {
        mockMvc.perform(get("/admin/films")).andExpect(status().isOk()).andExpect(view().name("admin/films"));
    }
}
