package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.base.ResponseMessage;
import dev.kienntt.demo.BE_Vinpearl.model.Tour;
import dev.kienntt.demo.BE_Vinpearl.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/tour")
public class TourController {
    @Autowired
    private TourService tourService;

    @PostMapping("/create")
    public ResponseMessage create(@RequestBody Tour tour) {
        tourService.save(tour);
        return new ResponseMessage(200, "Tạo tour thành công", "", null);
    }

    @GetMapping("/findAll")
    public ResponseMessage findAll() {
        return new ResponseMessage(200, "Success", tourService.findAll(), null);
    }

    @GetMapping("/detail/{id}")
    public ResponseMessage getTour(@PathVariable Long id) {
        Optional<Tour> tourOptional = tourService.findById(id);
        return tourOptional.map(site -> new ResponseMessage(200, "Success", site, null))
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

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
}
