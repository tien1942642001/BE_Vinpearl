package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.model.Hotel;
import dev.kienntt.demo.BE_Vinpearl.model.User;
import dev.kienntt.demo.BE_Vinpearl.repository.UserRepository;
import dev.kienntt.demo.BE_Vinpearl.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    LocalDateTime localDateTime = LocalDateTime.now();

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        LocalDateTime birthDate =
                Instant.ofEpochMilli(user.getBirthDateLong()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        String md5Password = DigestUtils.md5Hex(user.getPassword()).toUpperCase();
        user.setPassword(md5Password);
        user.setCreatedDate(localDateTime.toString());
        user.setCreatedBy(user.getCreator());
        user.setBirthDate(birthDate);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Page<User> searchUser(Long siteId, String name , String phone, Pageable pageable) {
        return userRepository.searchHotel(siteId, name, phone, pageable);
    }
}
