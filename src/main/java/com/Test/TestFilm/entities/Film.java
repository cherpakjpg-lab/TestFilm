package com.Test.TestFilm.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Film_ID", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "Film_Name", nullable = false, length = 100)
    private String filmName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Director_ID", nullable = false)
    private Director director;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Actor_ID", nullable = false)
    private Actor actor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Genre_ID", nullable = false)
    private Genre genre;

    @NotNull
    @Column(name = "ReleaseDate", nullable = false)
    private LocalDate releaseDate;

}