package ru.kkudlinkov.cinemaworldback.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kkudlinkov.cinemaworldback.Domain.model.Film;
import ru.kkudlinkov.cinemaworldback.Repository.FilmRepository;
import ru.kkudlinkov.cinemaworldback.Service.FilmService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;


@SpringBootTest
public class FilmServiceTest {

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private FilmService filmService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        filmService = new FilmService(filmRepository);
    }

    @Test
    public void testGetAllFilms() {
        // Arrange
        List<Film> expectedFilms = new ArrayList<>();

        var film1 = new Film();
        film1.setName("Test film 1");

        var film2 = new Film();
        film2.setName("Test Product 2");

        when(filmRepository.findAll()).thenReturn(expectedFilms);

        // Act
        List<Film> actualProducts = filmService.getAllFilms();

        // Assert
        verify(filmRepository, times(1)).findAll();
        Assertions.assertEquals(expectedFilms, actualProducts);
    }

    @Test
    public void testFindById() {
        // Arrange
        int filmdId = 1;
        var film = new Film();
        film.setName("Test Product");
        Optional<Film> expectedProduct = Optional.of(film);
        when(filmRepository.findById(filmdId)).thenReturn(expectedProduct);

        // Act
        Optional<Film> actualProduct = filmService.findById(filmdId);

        // Assert
        verify(filmRepository, times(1)).findById(filmdId);
        Assertions.assertEquals(expectedProduct, actualProduct);
    }

    @Test
    public void testGetById() {
        // Arrange
        int filmId = 1;

        var expectedFilm = new Film();
        expectedFilm.setName("Test Film");


        when(filmRepository.findById(filmId)).thenReturn(Optional.of(expectedFilm));

        // Act
        Film actualFilm = filmService.getById(filmId);

        // Assert
        verify(filmRepository, times(1)).findById(filmId);
        Assertions.assertEquals(expectedFilm, actualFilm);
    }

    @Test
    public void testGetAll() {
        List<Film> expectedFilms = new ArrayList<>();
        var film1 = new Film();
        var film2 = new Film();
        film1.setName("Test Film 1");
        film2.setName("Test Film 2");
        expectedFilms.add(film1);
        expectedFilms.add(film2);

        when(filmRepository.findAll()).thenReturn(expectedFilms);

        List<Film> actualFilms = filmService.getAllFilms();

        verify(filmRepository, times(1)).findAll();
        Assertions.assertEquals(expectedFilms, actualFilms);
    }

    @Test
    public void testSave() {
        // Arrange
        var film = new Film();
        film.setName("Test Film");

        when(filmRepository.save(film)).thenReturn(film);

        // Act
        Film actualFilm = filmService.save(film);

        // Assert
        verify(filmRepository, times(1)).save(film);
        Assertions.assertEquals(film, actualFilm);
    }

    @Test
    public void testDelete() {
        // Arrange
        int filmId = 1;

        // Act
        filmService.delete(filmId);

        // Assert
        verify(filmRepository, times(1)).deleteById(filmId);
    }

    @Test
    public void testGetFavouritesTotal() {
        // Arrange
        var film1 = new Film();
        film1.setId(1);
        var film2 = new Film();
        film2.setId(2);
        var film3 = new Film();
        film3.setId(3);
        var films = Set.of(film1, film2, film3);

        // Act
        double actualTotal = FilmService.getFavouritesTotal(films);

        // Assert
        Assertions.assertEquals(6, actualTotal);
    }

}
