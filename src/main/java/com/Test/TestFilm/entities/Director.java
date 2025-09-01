package com.Test.TestFilm.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "\"Director\"")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Director_ID\"", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "\"Diretor_FirstName\"", nullable = false, length = Integer.MAX_VALUE)
    private String diretorFirstname;

    @NotNull
    @Column(name = "\"Director_SecondName\"", nullable = false, length = Integer.MAX_VALUE)
    private String directorSecondname;

    @OneToMany(mappedBy = "director")
    private Set<Film> films = new LinkedHashSet<>();

}