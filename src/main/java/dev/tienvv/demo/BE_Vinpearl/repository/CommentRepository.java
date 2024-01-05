package dev.tienvv.demo.BE_Vinpearl.repository;

import dev.tienvv.demo.BE_Vinpearl.model.Comment;
import dev.tienvv.demo.BE_Vinpearl.model.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
    @Query("select c from Comment c WHERE " +
            "(:postId is null or c.postId = :postId)")
    Page<Comment> search(Long postId , Pageable pageable);

    Long countCommentByPostId(Long postId);
}
