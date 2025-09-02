package com.Test.TestFilm.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "director")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "director_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "director_first_name", nullable = false, length = Integer.MAX_VALUE)
    private String directorFirstname;

    @NotNull
    @Column(name = "director_second_name", nullable = false, length = Integer.MAX_VALUE)
    private String directorSecondname;

    @OneToMany(mappedBy = "director")
    private Set<Film> films = new LinkedHashSet<>();

}