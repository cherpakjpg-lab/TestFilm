package com.Test.TestFilm.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "\"Genre\"")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Genre_ID\"", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "\"GenreName\"", nullable = false, length = 100)
    private String genreName;

    @OneToMany(mappedBy = "genre")
    private Set<Film> films = new LinkedHashSet<>();

}