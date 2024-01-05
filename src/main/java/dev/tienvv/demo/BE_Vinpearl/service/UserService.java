package dev.tienvv.demo.BE_Vinpearl.service;

import dev.tienvv.demo.BE_Vinpearl.model.Hotel;
import dev.tienvv.demo.BE_Vinpearl.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Iterable findAll();

    Optional findById(Long id);

    User save(User user);

    User updateUser(User user);

    void deleteUser(Long id);

    User findByEmail(String email);

    Page<User> searchUser(Long siteId, String name, String phone, Pageable pageable);
}
