package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.Hotel;
import dev.kienntt.demo.BE_Vinpearl.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE " +
            "(:siteId is null or u.siteId = :siteId) and " +
            "(:name is null or u.fullName LIKE CONCAT('%',:name, '%')) and " +
            "(:phone is null or u.phone LIKE CONCAT('%',:phone, '%'))")
    Page<User> searchHotel(Long siteId, String name, String phone, Pageable pageable);
}
