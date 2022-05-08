package ru.yandex.practicum.filmorate.exceptions;

public class ValidationException extends  RuntimeException{
    public ValidationException(String message) {
        System.out.println(message);
    }
}
