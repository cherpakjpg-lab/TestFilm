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
@Table(name = "Actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Actor_ID", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "Actor_First_Name", nullable = false, length = 100)
    private String actorFirstName;

    @Size(max = 100)
    @NotNull
    @Column(name = "Actor_Last_Name", nullable = false, length = 100)
    private String actorLastName;

    @OneToMany(mappedBy = "actor")
    private Set<Film> films = new LinkedHashSet<>();

}