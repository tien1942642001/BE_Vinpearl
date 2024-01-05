package dev.tienvv.demo.BE_Vinpearl.controller;

import dev.tienvv.demo.BE_Vinpearl.base.ResponseMessage;
import dev.tienvv.demo.BE_Vinpearl.model.*;
import dev.tienvv.demo.BE_Vinpearl.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/search")
    public ResponseMessage search(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String description,
                                  @RequestParam(required = false) Long price,
                                  Pageable pageable) {
        Page<Service> listService = serviceService.search(name, description, price, pageable);
        return new ResponseMessage(200, "Success", listService, null);
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

    @PostMapping("/description/create")
    public ResponseMessage createDescription(@RequestBody Description description) {
        serviceService.saveDescription(description);
        return new ResponseMessage(200, "Success", "", null);
    }

    @GetMapping("/description/findAll")
    public ResponseMessage findAllDescription() {
        return new ResponseMessage(200, "Success", serviceService.findAllDescription(), null);
    }

    @PostMapping("/content/create")
    public ResponseMessage createContent(@RequestBody Content content) {
        serviceService.saveContent(content);
        return new ResponseMessage(200, "Success", "", null);
    }

    @GetMapping("/content/findAll")
    public ResponseMessage findAllContent() {
        return new ResponseMessage(200, "Success", serviceService.findAllContent(), null);
    }

    @PostMapping("/save")
    public ResponseMessage save(@RequestBody Service service) {
        serviceService.saveServiceDescriptionContent(service);
        return new ResponseMessage(200, "Success", "", null);
    }
}
