package dev.tienvv.demo.BE_Vinpearl.service;

import dev.tienvv.demo.BE_Vinpearl.model.Plane;

import java.util.Optional;

public interface PlaneService {
    Iterable findAll();

    Optional findById(Long id);

    Plane save(Plane plane);

    void remove(Long id);
}
