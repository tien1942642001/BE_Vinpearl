package dev.kienntt.demo.BE_Vinpearl.service;

import dev.kienntt.demo.BE_Vinpearl.model.ImageRoomType;
import dev.kienntt.demo.BE_Vinpearl.model.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface RoomTypeService {
    Iterable findAll();

    Optional findById(Long id);

    ImageRoomType save(RoomType room, MultipartFile[] images) throws IOException;

    RoomType save(RoomType room);

    void deleteRoomType(Long id);

    Page<RoomType> searchRoomTypesPage(Long numberPerson, Long hotelId, Long acreage, String name, Pageable pageable);

}
