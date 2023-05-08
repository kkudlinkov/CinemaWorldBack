package ru.kkudlinkov.cinemaworldback.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kkudlinkov.cinemaworldback.Domain.dto.UserEditDTO;
import ru.kkudlinkov.cinemaworldback.Domain.model.Film;
import ru.kkudlinkov.cinemaworldback.Domain.model.User;
import ru.kkudlinkov.cinemaworldback.Mapper.UserMapper;
import ru.kkudlinkov.cinemaworldback.Repository.UserRepository;
import ru.kkudlinkov.cinemaworldback.Service.AuthService;
import ru.kkudlinkov.cinemaworldback.Service.FilmService;
import ru.kkudlinkov.cinemaworldback.Service.UserService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private AuthService authService;

    @Mock
    private FilmService filmService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void testEncodePassword() {
        String password = "password";
        String encodedPassword = "encodedPassword";

        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        String result = userService.encodePassword(password);
        assertEquals(encodedPassword, result);
    }

    @Test
    public void testSave() {
        User user = new User();

        userService.save(user);

        verify(userRepository).save(user);
    }

    @Test
    public void testFindByUserName() {
        String username = "test";
        User user = new User();
        user.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByUserName(username);

        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
    }

    @Test
    public void testFindByEmail() {
        String email = "test@test.com";
        User user = new User();
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
    }

    @Test
    public void testFindById() {
        int id = 1;
        User user = new User();
        user.setId(id);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    public void testAddToFavourite() {
        Film film = new Film();
        film.setId(1);
        User user = new User();
        user.setId(1);
        user.setFilms(new HashSet<>());
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);

        when(authService.getAuthUser()).thenReturn(Optional.of(user));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        boolean result = userService.addToFavourite(film);

        assertTrue(result);
        assertEquals(1, user.getFilms().size());
        assertTrue(user.getFilms().contains(film));
    }

    @Test
    public void testRemoveFromFavourite() {
        Film film = new Film();
        film.setId(1);
        User user = new User();
        user.setId(1);
        user.setFilms(new HashSet<>(Arrays.asList(film)));
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);

        when(authService.getAuthUser()).thenReturn(Optional.of(user));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        boolean result = userService.removeFromFavourite(film);

        assertTrue(result);
        assertEquals(0, user.getFilms().size());
        assertFalse(user.getFilms().contains(film));
    }

    @Test
    public void testGetAllUsers() {
        List<User> expectedUsers = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<User> result = userService.getAllUsers();

        assertEquals(expectedUsers, result);
    }

    @Test
    public void testGetById() {
        User expectedUser = new User();
        when(userRepository.findById(1)).thenReturn(Optional.of(expectedUser));

        User result = userService.getById(1);

        assertEquals(expectedUser, result);
    }

    @Test
    public void testGetById_whenUserNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        User result = userService.getById(1);

        assertNull(result);
    }

}
