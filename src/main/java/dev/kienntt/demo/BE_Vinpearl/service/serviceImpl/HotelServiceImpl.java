package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.domain.dto.HotelDto;
import dev.kienntt.demo.BE_Vinpearl.domain.dto.ImageHotelDto;
import dev.kienntt.demo.BE_Vinpearl.model.Hotel;
import dev.kienntt.demo.BE_Vinpearl.model.ImageHotel;
import dev.kienntt.demo.BE_Vinpearl.repository.HotelRepository;
import dev.kienntt.demo.BE_Vinpearl.repository.ImageHotelRepository;
import dev.kienntt.demo.BE_Vinpearl.repository.ServiceRepository;
import dev.kienntt.demo.BE_Vinpearl.service.HotelService;
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
public class HotelServiceImpl implements HotelService {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    private static final String domain = "http://localhost:8080/";
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ImageHotelRepository imageHotelRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<Hotel> findAll() {
        return (List<Hotel>) hotelRepository.findAll();
    }

    @Override
    public Optional<Hotel> findById(Long id) {
        return hotelRepository.findById(id);
    }

    @Override
    public ImageHotel save(Hotel hotel, MultipartFile[] images) throws IOException {
        Hotel hotel1 = hotelRepository.save(hotel);

        for (MultipartFile image : images) {
            saveFile(hotel1.getId(), image);
        }
        return null;
    }

    @Override
    public void remove(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public Page<Hotel> searchHotel(Long siteId, String name, Long totalRoom, String phone, Pageable pageable) {
        return hotelRepository.searchHotel(siteId, name, totalRoom, phone, pageable);
    }

//    public Long getLowestServicePriceByRoomTypeId(Long roomTypeId) {
//        List<RoomTypeService> roomTypeServices = roomTypeServiceRepository.findByRoomTypeId(roomTypeId);
//        if (roomTypeServices.isEmpty()) {
//            // xử lý ngoại lệ nếu không tìm thấy dịch vụ
//        }
//        Long lowestPrice = null;
//        for (RoomTypeService roomTypeService : roomTypeServices) {
//            BigDecimal price = roomTypeService.getService().getPrice();
//            if (lowestPrice == null || price.compareTo(lowestPrice) < 0) {
//                lowestPrice = price;
//            }
//        }
//        return lowestPrice;
//    }


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

        ImageHotel imageHotel = new ImageHotel();
        imageHotel.setHotelId(id);
        imageHotel.setName(imagePath.resolve(image.getOriginalFilename()).toString());
        imageHotel.setPath(domain + imagePath.resolve(image.getOriginalFilename()));
        imageHotelRepository.save(imageHotel);
    }
}
