package dev.tienvv.demo.BE_Vinpearl.repository;

import dev.tienvv.demo.BE_Vinpearl.domain.dto.CustomerStats;
import dev.tienvv.demo.BE_Vinpearl.domain.dto.CustomerTop5Dto;
import dev.tienvv.demo.BE_Vinpearl.model.Customer;
import dev.tienvv.demo.BE_Vinpearl.model.Hotel;
import dev.tienvv.demo.BE_Vinpearl.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);

//    @Query("SELECT new dev.tienvv.demo.BE_Vinpearl.domain.dto.CustomerStats(" +
//            "COUNT(DISTINCT c.id), " +
//            "COUNT(DISTINCT CASE WHEN c.isNewCustomer = true THEN c.id ELSE null END), " +
//            "COUNT(DISTINCT CASE WHEN c.isNewCustomer = false THEN c.id ELSE null END)) " +
//            "FROM Customer c " +
//            "LEFT JOIN c.bookingTours bt " +
//            "LEFT JOIN c.bookingRooms br " +
//            "WHERE (bt.paymentDate BETWEEN :startTime AND :endTime) OR (br.paymentDate BETWEEN :startTime AND :endTime)")
//    Customer countCustomersByType(Long startTime,Long endTime);


    @Query("select new dev.tienvv.demo.BE_Vinpearl.domain.dto.CustomerTop5Dto(c.fullName, c.sex, c.email, c.phone, SUM(bt.paymentAmount)) from Customer c " +
            "left join BookingTour bt on bt.customerId = c.id WHERE " +
            "bt.paymentStatus = 1 and YEAR(bt.paymentDate) = YEAR(CURRENT_DATE) " +
            "GROUP BY c.fullName " +
            "order by sum(bt.paymentAmount) DESC")
    List<CustomerTop5Dto> getTop5();

    @Query("SELECT c FROM Customer c WHERE " +
            "(:email is null or c.email LIKE CONCAT('%',:email, '%')) and " +
            "(:name is null or c.fullName LIKE CONCAT('%',:name, '%')) and " +
            "(:phone is null or c.phone LIKE CONCAT('%',:phone, '%'))")
    Page<Customer> search(String email, String name, String phone, Pageable pageable);
}
