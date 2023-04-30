package ru.kkudlinkov.cinemaworldback.Domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    // OneToMany
    //    @Column(name = "genre")
    //    private String genre;
}
