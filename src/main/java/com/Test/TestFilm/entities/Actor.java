package com.Test.TestFilm.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "actor_first_name", nullable = false, length = 100)
    private String actorFirstName;

    @Size(max = 100)
    @NotNull
    @Column(name = "actor_last_name", nullable = false, length = 100)
    private String actorLastName;

    @OneToMany(mappedBy = "actor")
    @JsonManagedReference
    private Set<Film> films = new LinkedHashSet<>();

}