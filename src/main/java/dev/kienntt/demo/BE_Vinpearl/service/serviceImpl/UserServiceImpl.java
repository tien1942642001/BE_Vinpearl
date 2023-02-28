package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.model.User;
import dev.kienntt.demo.BE_Vinpearl.repository.UserRepository;
import dev.kienntt.demo.BE_Vinpearl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

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
}
