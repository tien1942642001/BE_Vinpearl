package dev.kienntt.demo.BE_Vinpearl.service;

import dev.kienntt.demo.BE_Vinpearl.domain.dto.CustomerTop5Dto;
import dev.kienntt.demo.BE_Vinpearl.model.Customer;
import dev.kienntt.demo.BE_Vinpearl.model.User;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Iterable findAll();

    Optional findById(Long id);

    Customer save(Customer customer);

    void remove(Long id);

    Customer findByEmail(String email);

    List<CustomerTop5Dto> getTop5();
}
