package dev.kienntt.demo.BE_Vinpearl.service;

import dev.kienntt.demo.BE_Vinpearl.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TourService {
    Iterable findAll();

    Optional findById(Long id);

    Tour save(Tour tour);

    void deleteTour(Long id);

    Page<Tour> searchTourPage(Long startTime, Long endTime, Pageable pageable);
}
