package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.base.ResponseMessage;
import dev.kienntt.demo.BE_Vinpearl.model.Service;
import dev.kienntt.demo.BE_Vinpearl.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @PostMapping("/create")
    public ResponseMessage create(@RequestBody Service service) {
        serviceService.save(service);
        return new ResponseMessage(200, "Thêm dịch vụ thành công", "", null);
    }

    @GetMapping("/findAll")
    public ResponseMessage findAll() {
        return new ResponseMessage(200, "Success", serviceService.findAll(), null);
    }

    @GetMapping("/detail")
    public ResponseMessage getSite(@PathVariable Long id) {
        Optional<Service> siteOptional = serviceService.findById(id);
        return siteOptional.map(site -> new ResponseMessage(200, "Success", site, null))
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

    @PutMapping("/updateSite")
    public ResponseMessage updateSite(@PathVariable Long id, @RequestBody Service service) {
        Optional<Service> airportOptional = serviceService.findById(id);
        return airportOptional.map(site1 -> {
            service.setId(site1.getId());
            serviceService.save(service);
            return new ResponseMessage(200, "Success", "", null);
        })
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

    @GetMapping("/deleteSite")
    public ResponseMessage deleteSite(@PathVariable Long id) {
        Optional<Service> serviceOptional = serviceService.findById(id);
        return serviceOptional.map(site -> {
            serviceService.remove(id);
            return new ResponseMessage(200, "Success", null, null);
        })
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }
}
