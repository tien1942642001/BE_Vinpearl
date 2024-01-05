package dev.tienvv.demo.BE_Vinpearl.service;

import dev.tienvv.demo.BE_Vinpearl.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommentService {
    Iterable findAll();

    Optional findById(Long id);

    Comment save(Comment comment);

    void remove(Long id);

    Page<Comment> search(Long postId, Pageable pageable);

    Long countCommentByPostId(Long postId);
}
