package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.model.Customer;
import dev.kienntt.demo.BE_Vinpearl.base.ResponseMessage;
import dev.kienntt.demo.BE_Vinpearl.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseMessage create(@RequestBody Customer customer) {
        customerService.save(customer);
        return new ResponseMessage(200, "Tạo tài khoản thành công", "", null);
    }

    @GetMapping("/findAll")
    public ResponseMessage findAll() {
        return new ResponseMessage(200, "Success", customerService.findAll(), null);
    }

    @GetMapping("/detail")
    public ResponseMessage getSite(@PathVariable Long id) {
        Optional<Customer> siteOptional = customerService.findById(id);
        return siteOptional.map(site -> new ResponseMessage(200, "Success", site, null))
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

    @PutMapping("/updateSite")
    public ResponseMessage updateSite(@PathVariable Long id, @RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerService.findById(id);
        return customerOptional.map(site1 -> {
            customer.setId(site1.getId());
            customerService.save(customer);
            return new ResponseMessage(200, "Success", "", null);
        })
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

    @GetMapping("/deleteSite")
    public ResponseMessage deleteSite(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        return customerOptional.map(site -> {
            customerService.remove(id);
            return new ResponseMessage(200, "Success", null, null);
        })
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }
}
