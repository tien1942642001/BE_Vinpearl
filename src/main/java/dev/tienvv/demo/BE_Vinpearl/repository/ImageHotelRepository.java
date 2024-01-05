package dev.tienvv.demo.BE_Vinpearl.repository;

import dev.tienvv.demo.BE_Vinpearl.domain.dto.ImageHotelDto;
import dev.tienvv.demo.BE_Vinpearl.model.Hotel;
import dev.tienvv.demo.BE_Vinpearl.model.ImageHotel;
import dev.tienvv.demo.BE_Vinpearl.model.ImageRoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ImageHotelRepository extends JpaRepository<ImageHotel, Long> {
    @Query("select new dev.tienvv.demo.BE_Vinpearl.domain.dto.ImageHotelDto(ih.id, ih.name, ih.path) from ImageHotel ih")
    List<ImageHotelDto> findByHotelId(Long hotelId);

    @Transactional
    void deleteByHotelId(Long hotelId);
}
