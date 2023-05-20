package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.domain.dto.CustomerStats;
import dev.kienntt.demo.BE_Vinpearl.domain.dto.CustomerTop5Dto;
import dev.kienntt.demo.BE_Vinpearl.model.Customer;
import dev.kienntt.demo.BE_Vinpearl.model.User;
import dev.kienntt.demo.BE_Vinpearl.repository.CustomerRepository;
import dev.kienntt.demo.BE_Vinpearl.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Iterable findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public List<CustomerTop5Dto> getTop5() {
        return customerRepository.getTop5();
    }

    @Override
    public Page<Customer> search(String email, String name , String phone, Pageable pageable) {
        return customerRepository.search(email, name, phone, pageable);
    }


//    @Override
//    public CustomerStats countCustomersByType(Long startTime, Long endTime) {
//        return customerRepository.countCustomersByType(startTime, endTime);
//    }
}
