package dev.kienntt.demo.BE_Vinpearl.service;

import dev.kienntt.demo.BE_Vinpearl.model.Role;

import java.util.Optional;

public interface RoleService {
    Iterable findAll();

    Optional findById(Long id);

    Role save(Role role);

    void deleteRole(Long id);
}
