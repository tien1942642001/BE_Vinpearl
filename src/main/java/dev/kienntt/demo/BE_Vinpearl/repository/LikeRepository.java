package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Long countByStatusAndPostId(Long status, Long postId);

    @Query("SELECT l FROM Like l WHERE " +
            "(l.postId = :postId) and (l.customerId = :customerId)")
    Like findByPostIdAndCustomerId(Long postId, Long customerId);

    List<Like> findAllByCustomerId(Long customerId);
}