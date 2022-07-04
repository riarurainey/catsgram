package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;

import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public List<User> findAll() {

        return new ArrayList<>(users.values());
    }

    public User create(User user) {

        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("User with this email address is already registered. " +
                    "Use a different email address");
        } else if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new InvalidEmailException("Email address is not specified. Please specify the email");
        } else {
            users.put(user.getEmail(), user);
        }

        return user;
    }

    public User put(User user) {
        if (users.containsKey(user.getEmail())) {
            users.put(user.getEmail(), user);
        } else if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new InvalidEmailException("Email address is not specified. Please specify the email");
        } else {
            users.put(user.getEmail(), user);
        }
        return user;
    }


    public User findUserByEmail(String email) {
        return users.get(email);
    }


}
