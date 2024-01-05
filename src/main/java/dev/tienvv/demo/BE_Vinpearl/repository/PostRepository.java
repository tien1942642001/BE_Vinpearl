package dev.tienvv.demo.BE_Vinpearl.repository;

import dev.tienvv.demo.BE_Vinpearl.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT new dev.tienvv.demo.BE_Vinpearl.domain.dto.PostListDto(p.id, p.name, p.path, p.publishedAt, COUNT(DISTINCT l.id), COUNT(DISTINCT c.id)) FROM Post p " +
            "left join Like l on p.id = l.postId " +
            "left join Comment c on p.id = c.postId " +
            "WHERE (:customerId is null or p.customerId = :customerId) " +
            "GROUP BY p.id, p.name ")
    Page<Post> search(Long customerId, Pageable pageable);

    List<Post> findAllByCustomerId(Long customerId);
}