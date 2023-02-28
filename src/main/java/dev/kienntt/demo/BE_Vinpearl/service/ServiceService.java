package dev.kienntt.demo.BE_Vinpearl.service;

import dev.kienntt.demo.BE_Vinpearl.model.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
    List<Service> findAll();

    Optional findById(Long id);

    Service save(Service service);

    void remove(Long id);
}
