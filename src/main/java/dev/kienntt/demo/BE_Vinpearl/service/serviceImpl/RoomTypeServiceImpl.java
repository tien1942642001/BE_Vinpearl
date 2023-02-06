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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
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
            imageRoomTypeRepository.save(imageRoomType);
        }
        return null;
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
    public Page<RoomType> searchRoomTypesPage(Long area, String name, Pageable pageable) {
//        return roomTypeRepository.searchRoomTypesPage(area, name, pageable);
        return roomTypeRepository.searchRoomTypesPage(area, name, pageable);
    }
}
