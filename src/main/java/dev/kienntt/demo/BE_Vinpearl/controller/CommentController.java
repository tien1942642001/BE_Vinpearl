package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.base.ResponseMessage;
import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import dev.kienntt.demo.BE_Vinpearl.model.Comment;
import dev.kienntt.demo.BE_Vinpearl.model.Like;
import dev.kienntt.demo.BE_Vinpearl.repository.LikeRepository;
import dev.kienntt.demo.BE_Vinpearl.service.CommentService;
import dev.kienntt.demo.BE_Vinpearl.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/posts/{postId}/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public ResponseMessage create(@PathVariable Long postId, @RequestBody Comment comment) {
        comment.setPostId(postId);
        commentService.save(comment);
        return new ResponseMessage(200, "Success", "", null);
    }

    @GetMapping("/findAll")
    public ResponseMessage findAll() {
        return new ResponseMessage(200, "Success", commentService.findAll(), null);
    }

    @GetMapping("/detail/{id}")
    public ResponseMessage getComment(@PathVariable Long id) {
        Optional<Comment> commentOptional = commentService.findById(id);
        return commentOptional.map(comment -> new ResponseMessage(200, "Success", comment, null))
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

    @PutMapping("/update/{id}")
    public ResponseMessage updateComment(@PathVariable Long postId,@PathVariable Long id, @RequestBody Comment comment) {
        Optional<Comment> commentOptional = commentService.findById(id);
        return commentOptional.map(comment1 -> {
                    comment.setPostId(postId);
                    comment.setId(id);
                    comment.setCustomerId(comment1.getCustomerId());
                    commentService.save(comment);
                    return new ResponseMessage(200, "Success", "", null);
                })
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

    @GetMapping("/delete/{id}")
    public ResponseMessage delete(@PathVariable Long postId, @PathVariable Long id) {
        Optional<Comment> commentOptional = commentService.findById(id);
        return commentOptional.map(comment -> {
                    commentService.remove(id);
                    return new ResponseMessage(200, "Success", null, null);
                })
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

    @GetMapping("/search")
    public ResponseMessage search(@PathVariable Long postId, Pageable pageable) {

        Page<Comment> list = commentService.search(postId, pageable);
        return new ResponseMessage(200, "Success", list, null);
    }

}
