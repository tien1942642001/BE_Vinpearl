package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.base.ResponseMessage;
import dev.kienntt.demo.BE_Vinpearl.model.Tour;
import dev.kienntt.demo.BE_Vinpearl.service.TourHotelService;
import dev.kienntt.demo.BE_Vinpearl.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/tour")
public class TourController {
    @Autowired
    private TourService tourService;

    @Autowired
    private TourHotelService tourHotelService;

    LocalDateTime localDateTime = LocalDateTime.now();

//    @PostMapping("/create")
//    public ResponseMessage create(@RequestBody Tour tour) {
//        tourService.save(tour);
//        return new ResponseMessage(200, "Tạo tour thành công", "", null);
//    }

    @PostMapping("/save")
    public ResponseMessage createTour(@RequestParam String name,
                                       @RequestParam String description,
                                       @RequestParam String inclusion,
                                      @RequestParam String termsConditions,
                                      @RequestParam Long leavingFromId,
                                      @RequestParam Long leavingToId,
                                      @RequestParam Long lengthStayId,
                                      @RequestParam Long suitableId,
                                      @RequestParam Long typeOfTourId,
                                      @RequestParam Long priceAdult,
                                      @RequestParam Long priceChildren,
                                      @RequestParam Long numberOfPeople,
                                      @RequestParam Long expirationDateMls,
                                      @RequestParam Long startDateMls,
                                      @RequestParam Long endDateMls,
                                      @RequestParam(required = false) Long id,
                                       @RequestParam(required = false) MultipartFile[] images) throws IOException {
        Tour tour = new Tour();
        tour.setCreatedDate(localDateTime.toString());
        tour.setName(name);
        tour.setDescription(description);
        tour.setInclusion(inclusion);
        tour.setTermsConditions(termsConditions);
        tour.setLeavingFromId(leavingFromId);
        tour.setLeavingToId(leavingToId);
        tour.setLengthStayId(lengthStayId);
        tour.setSuitableId(suitableId);
        tour.setTypeOfTourId(typeOfTourId);
        tour.setNumberOfPeople(numberOfPeople);
        tour.setExpirationDateMls(expirationDateMls);
        tour.setStartDateMls(startDateMls);
        tour.setEndDateMls(endDateMls);
        tour.setPriceAdult(priceAdult);
        tour.setPriceChildren(priceChildren);
        if (id != null) {
            tour.setId(id);
        }
        tourService.save(tour, images);
        return new ResponseMessage(200, "Success", "", null);
    }

    @GetMapping("/findAll")
    public ResponseMessage findAll() {
        return new ResponseMessage(200, "Success", tourService.findAll(), null);
    }

//    @GetMapping("/detail/{tourId}")
//    public ResponseMessage getHotelByTourId(@PathVariable Long tourId) {
//        return new ResponseMessage(200, "Success", tourHotelService.findTourHotelByTourId(tourId), null);
//    }

    @GetMapping("/detail/{tourId}")
    public ResponseMessage getHotelByTourId(@PathVariable Long tourId) {
        return new ResponseMessage(200, "Success", tourHotelService.findAvailableHotelsByTourIdLong(tourId), null);
    }
//    @GetMapping("/detail/{tourId}")
//    public ResponseEntity<List<Hotel>> getHotelByTourId(@PathVariable Long tourId) {
//        List<TourHotel> tourHotelList = tourHotelService.findTourHotelByTourId(tourId);
//
//        List<Hotel> hotelList = new ArrayList<>();
//        for (TourHotel tourHotel : tourHotelList) {
//            hotelList.add(tourHotel.getHotel());
//        }
//
//        return ResponseEntity.ok().body(hotelList);
//    }

//    @GetMapping("/detail/{id}")
//    public ResponseMessage getTour(@PathVariable Long id) {
//        Optional<Tour> tourOptional = tourService.findById(id);
//        return tourOptional.map(site -> new ResponseMessage(200, "Success", site, null))
//                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
//    }

//    @PutMapping("/updateSite")
//    public ResponseMessage updateSite(@PathVariable Long id, @RequestBody Site site) {
//        Optional<Site> siteOptional = siteService.findById(id);
//        return siteOptional.map(site1 -> {
//                    site.setId(site1.getId());
//                    siteService.save(site);
//                    return new ResponseMessage(200, "Success", "", null);
//                })
//                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
//    }
//
//    @GetMapping("/deleteSite")
//    public ResponseMessage deleteSite(@PathVariable Long id) {
//        Optional<Site> siteOptional = siteService.findById(id);
//        return siteOptional.map(site -> {
//                    siteService.remove(id);
//                    return new ResponseMessage(200, "Success", null, null);
//                })
//                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
//    }

    @GetMapping("/search")
    public ResponseMessage searchTour(@RequestParam(required = false) Long siteId,
                                      @RequestParam(required = false) String searchName,
                                      @RequestParam(required = false) Long status,
                                      @RequestParam(required = false) List<Long> lengthStayIds,
                                      @RequestParam(required = false) List<Long> suitableIds,
                                      @RequestParam(required = false) List<Long> typeOfTours,
                                      @RequestParam(required = false) Long customerId,
                                            Pageable pageable) {
        Page<Tour> listTour = tourService.searchTourPage(customerId, siteId, searchName, status, lengthStayIds, suitableIds, typeOfTours, pageable);
        return new ResponseMessage(200, "Success", listTour, null);
    }

    @GetMapping("/recommendation/{customerId}")
    public ResponseMessage getRecommendedTours(@PathVariable Long customerId) {
        // Lấy danh sách tour được đề xuất dựa trên các tương tác của người dùng
        List<Tour> recommendedTours = tourService.getRecommendedTours(customerId);

        return new ResponseMessage(200, "Success", recommendedTours, null);
    }
}
