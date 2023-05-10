package ru.kkudlinkov.cinemaworldback.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kkudlinkov.cinemaworldback.Domain.model.User;
import ru.kkudlinkov.cinemaworldback.Mapper.UserMapper;
import ru.kkudlinkov.cinemaworldback.Service.AdminService;
import ru.kkudlinkov.cinemaworldback.Service.FilmService;
import ru.kkudlinkov.cinemaworldback.Service.UserService;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
//@RunWith(SpringRunner.class)
public class AdminServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private FilmService productService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private AdminService adminService;


    @Test
    public void getAllUsers() {
        List<User> users = new ArrayList<>();
        when(userService.getAllUsers()).thenReturn(users);

        List<User> result = adminService.getAllUsers();

        assertEquals(users, result);
        verify(userService).getAllUsers();
    }

    @Test
    public void getUserById() {
        User user = new User();
        when(userService.getById(1)).thenReturn(user);

        User result = adminService.getUserById(1);

        assertEquals(user, result);
        verify(userService).getById(1);
    }


    @Test
    public void getAllFilms() {
        adminService.getAllFilms();

        verify(productService).getAllFilms();
    }
}
