package com.Test.TestFilm.controller;

import com.Test.TestFilm.dto.ActorDto;
import com.Test.TestFilm.dto.FilmCreateRequest;
import com.Test.TestFilm.entities.Actor;
import com.Test.TestFilm.entities.Director;
import com.Test.TestFilm.entities.Film;
import com.Test.TestFilm.entities.Genre;
import com.Test.TestFilm.repository.ActorRepository;
import com.Test.TestFilm.repository.DirectorRepository;
import com.Test.TestFilm.repository.FilmRepository;
import com.Test.TestFilm.repository.GenreRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Test.TestFilm.dto.FilmDto;

import java.util.Optional;
import java.util.stream.Collectors;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private GenreRepository genreRepository;


    @GetMapping("/api/actors")
    public List<ActorDto> getAllActors() {
        List<Actor> actors = actorRepository.findAll();
        return actors.stream()
                .map(actor -> {
                    List<String> filmNames = actor.getFilms().stream()
                            .map(Film::getFilmName)
                            .collect(Collectors.toList());

                    return new ActorDto(
                            actor.getId(),
                            actor.getActorFirstName(),
                            actor.getActorLastName(),
                            filmNames
                    );
                })
                .collect(Collectors.toList());
    }
    @GetMapping("/api/films")
    public List<FilmDto> getAllFilms(){
        List<Film> films = filmRepository.findAll();
        return films.stream()
                .map(film -> {
                    return new FilmDto(
                            film.getId(),
                            film.getFilmName(),
                            film.getReleaseDate(),
                            film.getDirector().getDirectorFirstname(),
                            film.getDirector().getDirectorSecondname(),
                            film.getActor().getActorFirstName(),       // добавляем имя актёра
                            film.getActor().getActorLastName(),      // добавляем фамилию актёра
                            film.getGenre().getGenreName()             // добавляем имя жанра
                    );
                })
                .collect(Collectors.toList());

    }

    @GetMapping("/api/films/{id}")
    List<FilmDto> getFilm(@RequestParam int film_id) {
        //return filmRepository.findById(film_id).orElseThrow();
        Optional<Film> films = filmRepository.findById(film_id);
        return films.stream()
                .map(film -> {
                    return new FilmDto(
                            film.getId(),
                            film.getFilmName(),
                            film.getReleaseDate(),
                            film.getDirector().getDirectorFirstname(),
                            film.getDirector().getDirectorSecondname(),
                            film.getActor().getActorFirstName(),       // добавляем имя актёра
                            film.getActor().getActorLastName(),      // добавляем фамилию актёра
                            film.getGenre().getGenreName()             // добавляем имя жанра
                    );
                })
                .collect(Collectors.toList());
    }

    @PostMapping("/api/films")
    public ResponseEntity<?> createFilm(@RequestBody @Valid FilmCreateRequest request) {

        // Получаем связанные сущности по ID
        Actor actor = actorRepository.findById(request.getActorId())
                .orElseThrow(() -> new RuntimeException("Актёр с ID " + request.getActorId() + " не найден"));

        Director director = directorRepository.findById(request.getDirectorId())
                .orElseThrow(() -> new RuntimeException("Режиссёр с ID " + request.getDirectorId() + " не найден"));

        Genre genre = genreRepository.findById(request.getGenreId())
                .orElseThrow(() -> new RuntimeException("Жанр с ID " + request.getGenreId() + " не найден"));

        // Создаём фильм
        Film film = new Film();
        film.setFilmName(request.getFilmName());
        film.setReleaseDate(request.getReleaseDate());
        film.setActor(actor);
        film.setDirector(director);
        film.setGenre(genre);

        // Сохраняем
        filmRepository.save(film);

        return ResponseEntity.status(HttpStatus.CREATED).body("Фильм успешно добавлен");
    }
    @PutMapping("/api/films/{id}")
    public ResponseEntity<?> updateFilm(@PathVariable Integer id, @RequestBody @Valid FilmCreateRequest request) {
        Optional<Film> optionalFilm = filmRepository.findById(id);

        if (optionalFilm.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Фильм с ID " + id + " не найден");
        }

        Film film = optionalFilm.get();

        // Находим связанные сущности
        Actor actor = actorRepository.findById(request.getActorId())
                .orElseThrow(() -> new RuntimeException("Актёр с ID " + request.getActorId() + " не найден"));

        Director director = directorRepository.findById(request.getDirectorId())
                .orElseThrow(() -> new RuntimeException("Режиссёр с ID " + request.getDirectorId() + " не найден"));

        Genre genre = genreRepository.findById(request.getGenreId())
                .orElseThrow(() -> new RuntimeException("Жанр с ID " + request.getGenreId() + " не найден"));

        // Обновляем поля
        film.setFilmName(request.getFilmName());
        film.setReleaseDate(request.getReleaseDate());
        film.setActor(actor);
        film.setDirector(director);
        film.setGenre(genre);

        // Сохраняем изменения
        filmRepository.save(film);

        return ResponseEntity.ok("Фильм с ID " + id + " успешно обновлён");
    }

    @DeleteMapping("/api/films/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable Integer id) {
        Optional<Film> optionalFilm = filmRepository.findById(id);

        if (optionalFilm.isPresent()) {
            filmRepository.deleteById(id);
            return ResponseEntity.ok("Фильм с ID " + id + " успешно удалён");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Фильм с ID " + id + " не найден");
        }
    }
}
