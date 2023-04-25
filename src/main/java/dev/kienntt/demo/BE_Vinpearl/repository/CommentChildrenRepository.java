package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.Comment;
import dev.kienntt.demo.BE_Vinpearl.model.CommentChildren;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentChildrenRepository extends PagingAndSortingRepository<CommentChildren, Long> {
    @Query("select cc from CommentChildren cc WHERE " +
            "(:commentId is null or cc.commentId = :commentId)")
    Page<Comment> search(Long commentId , Pageable pageable);
}
