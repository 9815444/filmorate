package ru.yandex.practicum.filmorate.validators;

import lombok.Data;
import ru.yandex.practicum.filmorate.exceptions.FilmValidationException;
import ru.yandex.practicum.filmorate.exceptions.UserValidationException;
import ru.yandex.practicum.filmorate.model.*;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.time.Duration;
import java.time.LocalDate;

public class Validator {

    public static void checkUserEmail(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            throw new UserValidationException("Email is not correct");
        }
    }

    public static void checkUserLogin(String login) {
        if (login == null || login.isEmpty() || login.contains(" ")) {
            throw new UserValidationException("Login is not correct");
        }
    }

    public static void checkUserName(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
    }

    public static void checkFilmName(Film film) {
        String name = film.getName();
        if (name == null
                || name.isEmpty()) {
            throw new FilmValidationException("Name of the film is not correct");
        }
    }

    public static void checkFilmDescription(Film film) {
        String description = film.getDescription();
        if (description.length() > 200) {
            throw new FilmValidationException("Description the film is not correct");
        }
    }

    public static void checkFilmReleaseDate(Film film) {
        LocalDate releaseDate = film.getReleaseDate();
        if (releaseDate == null
                || releaseDate.isBefore(LocalDate.of(1895, 12, 28))) {
            throw new FilmValidationException("Release date is not correct");
        }
    }

    public static void checkFilmDuration(Film film) {
        Duration duration = film.getDuration();
        if (duration.toSeconds() < 0) {
            throw new FilmValidationException("Duration of the film cannot be negative");
        }
    }

    public static void checkUserBirthday(User user) {
        if (user.getBirthday() == null || user.getBirthday().isAfter(LocalDate.now())) {
            throw new UserValidationException("Birthday is not correct");
        }
    }

    public static void checkUser(User user) {
        checkUserEmail(user.getEmail());
        checkUserLogin(user.getLogin());
        checkUserName(user);
        checkUserBirthday(user);
    }

    public static void checkFilm(Film film) {
        checkFilmName(film);
        checkFilmDescription(film);
        checkFilmReleaseDate(film);
        checkFilmDuration(film);
    }

//    public static boolean isValidLogin(String login) {
//        boolean result = true;
//        try {
//            InternetAddress emailAddr = new InternetAddress(email);
//            emailAddr.validate();
//        } catch (AddressException ex) {
//            result = false;
//        }
//        return result;
//    }
//
//    public static boolean isValidEmailAddress(String email) {
//        boolean result = true;
//        try {
//            InternetAddress emailAddr = new InternetAddress(email);
//            emailAddr.validate();
//        } catch (AddressException ex) {
//            result = false;
//        }
//        return result;
//    }
}
