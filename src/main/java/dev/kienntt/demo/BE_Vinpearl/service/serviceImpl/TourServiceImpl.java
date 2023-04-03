package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.model.*;
import dev.kienntt.demo.BE_Vinpearl.repository.*;
import dev.kienntt.demo.BE_Vinpearl.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    private static final String domain = "http://192.168.1.6:8080/";
    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private TourHotelRepository tourHotelRepository;

    @Autowired
    private ImageTourRepository imageTourRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<Tour> findAll() {
        return (List<Tour>) tourRepository.findAll();
    }

    @Override
    public Optional<Tour> findById(Long id) {
        return tourRepository.findById(id);
    }

    @Override
    public Tour save(Tour tour, MultipartFile[] images) throws IOException {
        LocalDateTime getExpirationDate =
                Instant.ofEpochMilli(tour.getExpirationDateMls()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime getStartDate =
                Instant.ofEpochMilli(tour.getStartDateMls()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime getEndDate =
                Instant.ofEpochMilli(tour.getEndDateMls()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        tour.setExpirationDate(getExpirationDate);
        tour.setExpirationDate(getStartDate);
        tour.setExpirationDate(getEndDate);
        tour.setRemainingOfPeople(tour.getNumberOfPeople());
        Tour tour1 = tourRepository.save(tour);
        if (tour.getId() != null) {
            // Xóa tất cả bản ghi ImageTour liên quan đến tour hiện tại
            imageTourRepository.deleteByTourId(tour1.getId());

            // Thêm các ảnh mới vào
            for (MultipartFile image : images) {
                saveFile(tour1.getId(), image);
            }
        } else {
            tour1.setCode(String.format("GN%06d", tour1.getId()));
            tourRepository.save(tour1);
            for (MultipartFile image : images) {
                saveFile(tour1.getId(), image);
            }
        }
        List<Hotel> hotelList = hotelRepository.findBySiteId(tour.getLeavingToId());
        for (Hotel hotel : hotelList) {
            Long minPriceRoomType = roomTypeRepository.findMinPriceByRoomTypeName(hotel.getId()) != null ? roomTypeRepository.findMinPriceByRoomTypeName(hotel.getId()) : 0;
            Long priceAdultTourHotel = tour.getPriceAdult() + minPriceRoomType;
            Long priceChildrenTourHotel = tour.getPriceChildren() + minPriceRoomType;
            TourHotel tourHotel = tourHotelRepository.findByTourIdAndHotelId(tour1.getId(), hotel.getId());
            if (tourHotel == null) {
                tourHotel = new TourHotel();
                tourHotel.setTourId(tour1.getId());
                tourHotel.setHotelId(hotel.getId());
            }
            tourHotel.setPriceAdult(priceAdultTourHotel);
            tourHotel.setPriceChildren(priceChildrenTourHotel);
            tourHotelRepository.save(tourHotel);
        }
        return null;
    }

    @Override
    public void deleteTour(Long id) {
        try{
            tourRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Page<Tour> searchTourPage(Long siteId, String searchName, Long status, List<Long> lengthStayIds, List<Long> suitableIds, List<Long> typeOfTours, Pageable pageable) {
//        PageRequest page_req = new PageRequest(0, buildingId, Sort.Direction.DESC, "idNode");
        return tourRepository.searchTourPage(siteId, searchName, status, lengthStayIds, suitableIds, typeOfTours, pageable);
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

        ImageTour imageTour = new ImageTour();
        imageTour.setTourId(id);
        imageTour.setName(imagePath.resolve(image.getOriginalFilename()).toString());
        imageTour.setPath(domain + imagePath.resolve(image.getOriginalFilename()));
        imageTourRepository.save(imageTour);
    }
}
