package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.model.BookingTour;
import dev.kienntt.demo.BE_Vinpearl.model.Comment;
import dev.kienntt.demo.BE_Vinpearl.repository.CommentRepository;
import dev.kienntt.demo.BE_Vinpearl.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Iterable findAll() {
        return null;
    }

    @Override
    public Optional findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void remove(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Page<Comment> search(Long postId, Pageable pageable) {
        return commentRepository.search(postId,  pageable);
    }
}
