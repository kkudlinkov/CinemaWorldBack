package ru.kkudlinkov.cinemaworldback.Domain.model;

//Сущность БД

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "films")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Film {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    /*
     * Описание фильма
     */
    @Column(name = "description")
    private String description;

    /*
     * Главный актер
     */

    @Column(name = "actor")
    private String actor;

    /*
     * Картинка товара
     */
    @Column(name = "image")
    private String image;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_films",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id == film.id && Objects.equals(name, film.name) && Objects.equals(description, film.description) && Objects.equals(actor, film.actor) && Objects.equals(image, film.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, actor, image);
    }
}
