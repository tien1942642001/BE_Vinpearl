package dev.kienntt.demo.BE_Vinpearl.service;

import dev.kienntt.demo.BE_Vinpearl.model.Comment;
import dev.kienntt.demo.BE_Vinpearl.model.Like;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LikeService {

    Long countLikeByPostId(Long status, Long postId);

    Like save(Like like);

    Like findByPostIdAndCustomerId(Long postId, Long customerId);
}
