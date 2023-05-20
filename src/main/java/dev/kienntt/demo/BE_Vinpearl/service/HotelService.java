package dev.kienntt.demo.BE_Vinpearl.service;

import dev.kienntt.demo.BE_Vinpearl.domain.dto.HotelDto;
import dev.kienntt.demo.BE_Vinpearl.model.Hotel;
import dev.kienntt.demo.BE_Vinpearl.model.ImageHotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface HotelService {
    List<Hotel> findAll();

    Optional<Hotel> findById(Long id);

    ImageHotel save(Hotel hotel, MultipartFile[] images) throws IOException;

    void remove(Long id);

    Page<Hotel> searchHotel(Long siteId, String name, Long totalRoom, String phone, Pageable pageable);

    Page<Hotel> getListHotel(Long siteId, String name, Long totalRoom, String phone, Pageable pageable);

    List<HotelDto> getListHotelByCustomer(Long customerId, Long siteId);
}
