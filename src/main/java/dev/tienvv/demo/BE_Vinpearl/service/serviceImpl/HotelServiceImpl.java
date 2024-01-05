package dev.tienvv.demo.BE_Vinpearl.service.serviceImpl;

import dev.tienvv.demo.BE_Vinpearl.domain.dto.HotelDto;
import dev.tienvv.demo.BE_Vinpearl.domain.dto.ImageHotelDto;
import dev.tienvv.demo.BE_Vinpearl.domain.request.BookingTourStatistic;
import dev.tienvv.demo.BE_Vinpearl.model.BookingTour;
import dev.tienvv.demo.BE_Vinpearl.model.Hotel;
import dev.tienvv.demo.BE_Vinpearl.model.ImageHotel;
import dev.tienvv.demo.BE_Vinpearl.repository.HotelRepository;
import dev.tienvv.demo.BE_Vinpearl.repository.ImageHotelRepository;
import dev.tienvv.demo.BE_Vinpearl.repository.ServiceRepository;
import dev.tienvv.demo.BE_Vinpearl.service.HotelService;
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
    private static final String domain = "https://localhost:8443/";
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
        if (hotel.getId() != null) {
            if (images != null) {
                // Xóa tất cả bản ghi ImageTour liên quan đến tour hiện tại
                imageHotelRepository.deleteByHotelId(hotel1.getId());

                // Thêm các ảnh mới vào
                for (MultipartFile image : images) {
                    saveFile(hotel1.getId(), image);
                }
            }
        } else {
            hotelRepository.save(hotel1);
            for (MultipartFile image : images) {
                saveFile(hotel1.getId(), image);
            }
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

    @Override
    public Page<Hotel> getListHotel(Long siteId, String name, Long totalRoom, String phone, Pageable pageable) {
        Page<Hotel> test = hotelRepository.getListHotel(siteId, name, totalRoom, phone, pageable);
        System.out.println(test);
        return hotelRepository.getListHotel(siteId, name, totalRoom, phone, pageable);
    }

    @Override
    public List<HotelDto> getListHotelByCustomer(Long customerId, Long siteId) {

        return hotelRepository.getListHotelByCustomer(customerId, siteId);
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

        ImageHotel imageHotel = new ImageHotel();
        imageHotel.setHotelId(id);
        imageHotel.setName(imagePath.resolve(image.getOriginalFilename()).toString());
        imageHotel.setPath(domain + imagePath.resolve(image.getOriginalFilename()));
        imageHotelRepository.save(imageHotel);
    }
}
