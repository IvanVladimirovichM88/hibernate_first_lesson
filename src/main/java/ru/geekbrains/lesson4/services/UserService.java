package ru.geekbrains.lesson4.services;

import ru.geekbrains.lesson4.entity.User;

import java.util.List;

public interface UserService {
    User get(Long id);
    List<User> getAll();
    void save(User user);
    void remove (User user);
}
