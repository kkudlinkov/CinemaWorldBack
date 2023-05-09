package ru.kkudlinkov.cinemaworldback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kkudlinkov.cinemaworldback.Domain.model.Film;

import java.util.List;


@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {

}
