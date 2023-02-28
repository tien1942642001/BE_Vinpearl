package dev.kienntt.demo.BE_Vinpearl.service;

import dev.kienntt.demo.BE_Vinpearl.model.Customer;

import java.util.Optional;

public interface CustomerService {
    Iterable findAll();

    Optional findById(Long id);

    Customer save(Customer customer);

    void remove(Long id);
}
