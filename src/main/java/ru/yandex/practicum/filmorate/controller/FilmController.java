package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.FilmValidationException;
import ru.yandex.practicum.filmorate.model.*;
import ru.yandex.practicum.filmorate.validators.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.yandex.practicum.filmorate.validators.Validator.*;

@RestController
@RequestMapping("/")
@Slf4j
public class FilmController {

    private Map<Integer, Film> films = new HashMap<>();
    private int lastId = 0;

    @PostMapping(value = "/films")
    public Film create(@RequestBody Film film) {
        checkFilm(film);
        film.setId(++lastId);
        films.put(film.getId(), film);
        log.info("Added film {}", film);
        return film;
    }

    @PutMapping(value = "/films")
    public Film update(@RequestBody Film film) {
        if (film.getId() == 0) {
            throw  new FilmValidationException("Id is not correct");
        } else if (!films.containsKey(film.getId())) {
            throw  new FilmValidationException("Id is not correct");
        }
        checkFilm(film);
        log.info("Update film. New data {}", film);
        films.put(film.getId(), film);
        return film;
    }


    @GetMapping(value = "/films")
    public List<Film> getAll() {
        return new ArrayList<>(films.values());
    }
}
