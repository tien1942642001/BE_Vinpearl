package dev.tienvv.demo.BE_Vinpearl.service;

import dev.tienvv.demo.BE_Vinpearl.domain.dto.CustomerStats;
import dev.tienvv.demo.BE_Vinpearl.domain.dto.CustomerTop5Dto;
import dev.tienvv.demo.BE_Vinpearl.model.Customer;
import dev.tienvv.demo.BE_Vinpearl.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Iterable findAll();

    Optional findById(Long id);

    Customer save(Customer customer);

    void remove(Long id);

    Customer findByEmail(String email);

    List<CustomerTop5Dto> getTop5();

    Page<Customer> search(String email, String name, String phone, Pageable pageable);


//    CustomerStats countCustomersByType(Long startTime, Long endTime);
}
