package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.model.*;
import dev.kienntt.demo.BE_Vinpearl.repository.ImageRoomTypeRepository;
import dev.kienntt.demo.BE_Vinpearl.repository.RoomTypeRepository;
import dev.kienntt.demo.BE_Vinpearl.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    private static final String domain = "http://localhost:8080/";

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private ImageRoomTypeRepository imageRoomTypeRepository;

    @Override
    public List<RoomType> findAll() {
        return (List<RoomType>) roomTypeRepository.findAll();
    }

    @Override
    public Optional<RoomType> findById(Long id) {
        return roomTypeRepository.findById(id);
    }

    @Override
    public ImageRoomType save(RoomType roomType, MultipartFile[] images) throws IOException {
        RoomType roomType1 = roomTypeRepository.save(roomType);
        if (roomType.getId() != null) {
            if (images != null) {
                imageRoomTypeRepository.deleteByRoomTypeId(roomType1.getId());

                // Thêm các ảnh mới vào
                for (MultipartFile image : images) {
                    saveFile(roomType1.getId(), image);
                }
            }
        } else {
            roomTypeRepository.save(roomType1);
            for (MultipartFile image : images) {
                saveFile(roomType1.getId(), image);
            }
        }
        return null;
    }

    @Override
    public RoomType save(RoomType room) {
        return roomTypeRepository.save(room);
    }

    @Override
    public void deleteRoomType(Long id) {
        try{
            roomTypeRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public Page<RoomType> searchRoomTypesPage(Long numberPerson, String hotelName, Long acreage, String name, Long startTime, Long endTime, Pageable pageable) {
        LocalDateTime startDate = startTime != null ? Instant.ofEpochMilli(startTime).atZone(ZoneId.systemDefault()).toLocalDateTime() : null;
        LocalDateTime endDate = endTime != null ? Instant.ofEpochMilli(endTime).atZone(ZoneId.systemDefault()).toLocalDateTime() : null;
        return roomTypeRepository.searchRoomTypesPage(numberPerson, hotelName, acreage, name, startDate, endDate, pageable);
    }

    @Override
    public List<RoomType> findRoomTypeByHotelId(Long hotelId) {
        return roomTypeRepository.findRoomTypeByHotelId(hotelId);
    }

    public void saveFile(Long id, MultipartFile image) throws IOException {
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images");
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
        Path files = CURRENT_FOLDER.resolve(staticPath)
                .resolve(imagePath).resolve(image.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(files)) {
            os.write(image.getBytes());
        }

        ImageRoomType imageRoomType = new ImageRoomType();
        imageRoomType.setRoomTypeId(id);
        imageRoomType.setName(imagePath.resolve(image.getOriginalFilename()).toString());
        imageRoomType.setPath(domain + imagePath.resolve(image.getOriginalFilename()));
        imageRoomTypeRepository.save(imageRoomType);
    }
}
