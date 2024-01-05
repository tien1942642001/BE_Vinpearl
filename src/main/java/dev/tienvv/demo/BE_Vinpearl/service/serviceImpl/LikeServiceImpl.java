package dev.tienvv.demo.BE_Vinpearl.service.serviceImpl;

import dev.tienvv.demo.BE_Vinpearl.model.Like;
import dev.tienvv.demo.BE_Vinpearl.repository.LikeRepository;
import dev.tienvv.demo.BE_Vinpearl.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Override
    public Long countLikeByPostId(Long status, Long postId) {
        return likeRepository.countByStatusAndPostId(1L, postId);
    }

    @Override
    public Like save(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public Like findByPostIdAndCustomerId(Long postId, Long customerId) {
        return likeRepository.findByPostIdAndCustomerId(postId, customerId);
    }
}
