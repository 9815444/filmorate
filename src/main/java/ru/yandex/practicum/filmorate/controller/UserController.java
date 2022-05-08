package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validators.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class UserController {

    Map<Integer, User> users = new HashMap<>();
    int lastId = 0;

    @PostMapping(value = "/users")
    public User create(@RequestBody User user) {
        Validator.checkUser(user);
        user.setId(++lastId);
        users.put(user.getId(), user);
        return user;
    }

    @PutMapping(value = "/users")
    public User update(@RequestBody User user) {
        if (user.getId() == 0) {
            throw new ValidationException("Id is not correct");
        } else if (!users.containsKey(user.getId())) {
            throw new ValidationException("Id is not correct");
        }
        Validator.checkUser(user);
        users.put(user.getId(), user);
        return user;
    }

    @GetMapping(value = "/users")
    public List<User> getAll() {
        return new ArrayList<User>(users.values());
    }
}
