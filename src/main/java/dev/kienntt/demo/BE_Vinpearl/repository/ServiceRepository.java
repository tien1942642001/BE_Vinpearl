package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.RoomType;
import dev.kienntt.demo.BE_Vinpearl.model.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends PagingAndSortingRepository<Service, Long> {
    List<Service> findByRoomTypeId(Long roomTypeId);

    @Query(value = "SELECT MIN(s.price) FROM Service s inner join RoomType r on s.roomTypeId = r.id WHERE s.roomTypeId = :roomTypeId")
    public Long findMinPriceByRoomTypeId(Long roomTypeId);

    @Query("SELECT s FROM Service s WHERE " +
            "(:name is null or s.name LIKE CONCAT('%',:name, '%'))")
    Page<Service> search(String name, Pageable pageable);
}
