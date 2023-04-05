package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.config.JwtTokenProvider;
import dev.kienntt.demo.BE_Vinpearl.model.Customer;
import dev.kienntt.demo.BE_Vinpearl.base.ResponseMessage;
import dev.kienntt.demo.BE_Vinpearl.model.User;
import dev.kienntt.demo.BE_Vinpearl.service.CustomerService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseMessage create(@RequestBody Customer customer) {
        String md5Password = DigestUtils.md5Hex(customer.getPassword()).toUpperCase();
        customer.setPassword(md5Password);
        customerService.save(customer);
        return new ResponseMessage(200, "Tạo tài khoản thành công", "", null);
    }

    @PostMapping("/login")
    public ResponseMessage login(@RequestBody Customer customer) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Customer customerDb = customerService.findByEmail(customer.getEmail());
        String md5Password = DigestUtils.md5Hex(customer.getPassword()).toUpperCase();
        if (!customerDb.getEmail().equals(customer.getEmail())) {
            return new ResponseMessage(400, "Fail","Tài khoản không tồn tại", null);
        } else if (!customerDb.getPassword().equals(md5Password)) {
            return new ResponseMessage(400, "Fail", "Mật khẩu không đúng", null);
        }
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        customerDb.setToken(jwtTokenProvider.createJwtSignedHMAC1(customerDb));
        return new ResponseMessage(200, "Success", customerDb, null);
    }

    @GetMapping("/findAll")
    public ResponseMessage findAll() {
        return new ResponseMessage(200, "Success", customerService.findAll(), null);
    }

    @GetMapping("/detail/{id}")
    public ResponseMessage getSite(@PathVariable Long id) {
        Optional<Customer> siteOptional = customerService.findById(id);
        return siteOptional.map(site -> new ResponseMessage(200, "Success", site, null))
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

    @PutMapping("/update/{id}")
    public ResponseMessage update(@PathVariable Long id, @RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerService.findById(id);
        return customerOptional.map(customer1 -> {
            customer.setId(customer1.getId());
            customer.setPassword(customer1.getPassword());
            customerService.save(customer);
            return new ResponseMessage(200, "Success", "", null);
        })
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

    @GetMapping("/delete")
    public ResponseMessage delete(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        return customerOptional.map(site -> {
            customerService.remove(id);
            return new ResponseMessage(200, "Success", null, null);
        })
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

    @GetMapping("/search")
    public ResponseMessage search(@RequestParam(required = false) String email,
                                       @RequestParam(required = false) String name,
                                       @RequestParam(required = false) String phone,
                                       Pageable pageable) {
        Page<Customer> listCustomer = customerService.search(email, name, phone, pageable);
        return new ResponseMessage(200, "Success", listCustomer, null);
    }
}
