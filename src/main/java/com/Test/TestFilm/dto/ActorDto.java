package com.Test.TestFilm.dto;

import lombok.Getter;
import lombok.Setter;
import com.Test.TestFilm.dto.FilmDto;
import java.util.List;

@Getter
@Setter
public class ActorDto {
    private Integer id;
    private String actorFirstName;
    private String actorLastName;
    private List<String> filmNames;

    // Constructors
    public ActorDto() {}

    public ActorDto(Integer id, String actorFirstName, String actorLastName, List<String> filmNames) {
        this.id = id;
        this.actorFirstName = actorFirstName;
        this.actorLastName = actorLastName;
        this.filmNames = filmNames;
    }


}
