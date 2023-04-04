package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.ImageRoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ImageRoomTypeRepository extends JpaRepository<ImageRoomType, Long> {
    @Transactional
    void deleteByRoomTypeId(Long roomTypeId);
}
