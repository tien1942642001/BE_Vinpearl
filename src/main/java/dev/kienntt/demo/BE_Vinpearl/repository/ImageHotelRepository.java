package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.ImageHotel;
import dev.kienntt.demo.BE_Vinpearl.model.ImageRoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageHotelRepository extends JpaRepository<ImageHotel, Long> {
}
