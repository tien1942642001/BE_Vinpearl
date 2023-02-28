package dev.kienntt.demo.BE_Vinpearl.service;

import dev.kienntt.demo.BE_Vinpearl.model.User;

import java.util.Optional;

public interface UserService {

    Iterable findAll();

    Optional findById(Long id);

    User save(User user);

    User updateUser(User user);

    void deleteUser(Long id);

    User findByEmail(String email);
}
