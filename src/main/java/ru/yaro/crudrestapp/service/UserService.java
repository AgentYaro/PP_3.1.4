package ru.yaro.crudrestapp.service;

import ru.yaro.crudrestapp.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);

    List<User> getAllUsers();

    void addUser(User user);
    User getByEmail(String email);
    void deleteUser(User user);

    void updateUser(User user);

    void deleteUserById(long id);
}
