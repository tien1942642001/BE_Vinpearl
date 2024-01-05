package dev.tienvv.demo.BE_Vinpearl.service;

import dev.tienvv.demo.BE_Vinpearl.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    List<Restaurant> findAll();

    Optional<Restaurant> findById(Long id);

    Restaurant save(Restaurant restaurant);

    void remove(Long id);
}
