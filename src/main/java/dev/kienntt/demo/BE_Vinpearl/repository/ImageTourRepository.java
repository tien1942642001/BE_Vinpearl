package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.ImageTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ImageTourRepository extends JpaRepository<ImageTour, Long> {
    @Transactional
    void deleteByTourId(Long tourId);
}
