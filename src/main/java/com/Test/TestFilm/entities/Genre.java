package com.Test.TestFilm.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "genre_name", nullable = false, length = 100)
    private String genreName;

    @OneToMany(mappedBy = "genre")
    @JsonManagedReference
    private Set<Film> films = new LinkedHashSet<>();

}