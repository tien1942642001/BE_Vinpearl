package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.base.ResponseMessage;
import dev.kienntt.demo.BE_Vinpearl.model.Hotel;
import dev.kienntt.demo.BE_Vinpearl.model.Post;
import dev.kienntt.demo.BE_Vinpearl.model.Restaurant;
import dev.kienntt.demo.BE_Vinpearl.model.Tour;
import dev.kienntt.demo.BE_Vinpearl.service.PostService;
import dev.kienntt.demo.BE_Vinpearl.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/post")
public class PostController {
    @Autowired
    private PostService postService;

    LocalDateTime localDateTime = LocalDateTime.now();

    @PostMapping("/create")
    public ResponseMessage create(@RequestBody Post post) {
        System.out.println("local:" +localDateTime);
        post.setPublishedAt(localDateTime.toString());
        postService.save(post);
        return new ResponseMessage(200, "Success", "", null);
    }

    @PostMapping("/update")
    public ResponseMessage update(@RequestBody Post post) {
        post.setPublishedAt(localDateTime.toString());
        postService.save(post);
        return new ResponseMessage(200, "Success", "", null);
    }

    @GetMapping("/detail/{id}")
    public ResponseMessage detail(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        return postOptional.map(post -> new ResponseMessage(200, "Success", post, null))
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

    @GetMapping("/search")
    public ResponseMessage searchTour(@RequestParam(required = false) Long customerId,
                                      Pageable pageable) {
        Page<Post> listPost = postService.searchPost(customerId, pageable);
        return new ResponseMessage(200, "Success", listPost, null);
    }

    @PostMapping("/add")
    public ResponseMessage addPost(@RequestParam String name,
                                       @RequestParam String content,
                                       @RequestParam Long siteId,
                                       @RequestParam (required = false) Long hotelId,
                                        @RequestParam Long customerId,
                                       @RequestParam MultipartFile images) throws IOException {
        Post post = new Post();
        post.setPublishedAt(localDateTime.toString());
        post.setName(name);
        post.setContent(content);
        post.setSiteId(siteId);
        if (hotelId != null) {
            post.setHotelId(hotelId);
        }
        post.setCustomerId(customerId);
        postService.add(post, images);
        return new ResponseMessage(200, "Success", "", null);
    }
}
