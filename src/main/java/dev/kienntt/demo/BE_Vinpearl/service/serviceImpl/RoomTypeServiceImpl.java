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

        for (MultipartFile image : images) {
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
            imageRoomType.setRoomTypeId(roomType1.getId());
            imageRoomType.setName(imagePath.resolve(image.getOriginalFilename()).toString());
            imageRoomType.setPath(domain + imagePath.resolve(image.getOriginalFilename()));
            imageRoomTypeRepository.save(imageRoomType);
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
    public Page<RoomType> searchRoomTypesPage(Long numberPerson, String hotelName, Long acreage, String name, Pageable pageable) {
//        return roomTypeRepository.searchRoomTypesPage(acreage, name, pageable);
        return roomTypeRepository.searchRoomTypesPage(numberPerson, hotelName, acreage, name, pageable);
    }

    @Override
    public List<RoomType> findRoomTypeByHotelId(Long hotelId) {
        return roomTypeRepository.findRoomTypeByHotelId(hotelId);
    }
}
