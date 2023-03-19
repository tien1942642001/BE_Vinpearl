package dev.kienntt.demo.BE_Vinpearl.service;

import dev.kienntt.demo.BE_Vinpearl.model.Hotel;
import dev.kienntt.demo.BE_Vinpearl.model.ImageHotel;
import dev.kienntt.demo.BE_Vinpearl.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface PostService {
    Iterable findAll();

    Optional findById(Long id);

    Post save(Post post);

    void remove(Long id);

    Page<Post> searchPost(Long id, Pageable pageable);

    Post add(Post post, MultipartFile images) throws IOException;
}
