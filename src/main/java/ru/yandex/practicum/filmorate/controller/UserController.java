package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.UserValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.yandex.practicum.filmorate.validators.Validator.*;

@RestController
@RequestMapping("/")
@Slf4j
public class UserController {

    Map<Integer, User> users = new HashMap<>();
    int lastId = 0;

    @PostMapping(value = "/users")
    public User create(@RequestBody User user) {
        checkUser(user);
        user.setId(++lastId);
        users.put(user.getId(), user);
        log.info("Added user {}", user);
        return user;
    }

    @PutMapping(value = "/users")
    public User update(@RequestBody User user) {
        if (user.getId() == 0) {
            throw new UserValidationException("Id is not correct");
        } else if (!users.containsKey(user.getId())) {
            throw new UserValidationException("Id is not correct");
        }
        checkUser(user);
        log.info("Update user. New data {}", user);
        users.put(user.getId(), user);
        return user;
    }

    @GetMapping(value = "/users")
    public List<User> getAll() {
        return new ArrayList<User>(users.values());
    }
}
