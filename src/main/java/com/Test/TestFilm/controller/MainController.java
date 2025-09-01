package com.Test.TestFilm.controller;

import com.Test.TestFilm.entities.Actor;
import com.Test.TestFilm.entities.Film;
import com.Test.TestFilm.repository.ActorRepository;
import com.Test.TestFilm.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final FilmRepository filmRepository;
    private final ActorRepository actorRepository;

    @GetMapping("/api/films")
    public List<Film> getAll(){
        return filmRepository.findAll();
    }

    @GetMapping("/api/actors")
    public List<Actor> getAllActors(){
        return actorRepository.findAll();
    }

}
