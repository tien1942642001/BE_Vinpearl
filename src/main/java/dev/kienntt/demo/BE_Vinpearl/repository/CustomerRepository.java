package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.domain.dto.CustomerTop5Dto;
import dev.kienntt.demo.BE_Vinpearl.model.Customer;
import dev.kienntt.demo.BE_Vinpearl.model.Hotel;
import dev.kienntt.demo.BE_Vinpearl.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);

    @Query("select new dev.kienntt.demo.BE_Vinpearl.domain.dto.CustomerTop5Dto(c.fullName, c.sex, c.email, c.phone, SUM(bt.paymentAmount)) from Customer c " +
            "left join BookingTour bt on bt.customerId = c.id WHERE " +
            "bt.paymentStatus = 1 and YEAR(bt.paymentDate) = YEAR(CURRENT_DATE) " +
            "GROUP BY c.fullName " +
            "order by sum(bt.paymentAmount) DESC")
    List<CustomerTop5Dto> getTop5();
}
