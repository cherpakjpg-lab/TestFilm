package com.Test.TestFilm.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter

public class FilmDto {
    private Integer id;
    private String filmName;
    private LocalDate releaseDate;
    private String directorFirstname;
    private String directorSecondname;
    private String actorFirstname;
    private String actorSecondname;
    private String genreName;

    // Конструктор
    public FilmDto(Integer id, String filmName, LocalDate releaseDate,
                   String directorFirstname, String directorSecondname,
                   String actorFirstname, String actorSecondname, String genreName) {
        this.id = id;
        this.filmName = filmName;
        this.releaseDate = releaseDate;
        this.directorFirstname = directorFirstname;
        this.directorSecondname = directorSecondname;
        this.actorFirstname = actorFirstname;
        this.actorSecondname = actorSecondname;
        this.genreName = genreName;
    }
}
