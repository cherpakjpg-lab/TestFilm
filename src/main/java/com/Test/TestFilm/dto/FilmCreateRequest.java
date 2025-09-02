package com.Test.TestFilm.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmCreateRequest {
    @NotNull
    private String filmName;

    @NotNull
    private LocalDate releaseDate;

    @NotNull
    private Integer actorId;

    @NotNull
    private Integer directorId;

    @NotNull
    private Integer genreId;
}
