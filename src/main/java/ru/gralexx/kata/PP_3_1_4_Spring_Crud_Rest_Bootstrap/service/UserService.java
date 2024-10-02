package ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.service;


import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUserByUsername(String username);

    User getUserById(Long id);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    void deleteUserByUsername(String username);
}
