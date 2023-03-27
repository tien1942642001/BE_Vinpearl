package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.model.ImageHotel;
import dev.kienntt.demo.BE_Vinpearl.model.Post;
import dev.kienntt.demo.BE_Vinpearl.repository.PostRepository;
import dev.kienntt.demo.BE_Vinpearl.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    private static final String domain = "http://192.168.1.6:8080/";
    @Autowired
    private PostRepository postRepository;

    @Override
    public Iterable findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Page<Post> searchPost(Long id, Pageable pageable) {
        return postRepository.search(id, pageable);
    }

    @Override
    public Post add(Post post, MultipartFile image) throws IOException {
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images");
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
        Path files = CURRENT_FOLDER.resolve(staticPath)
                .resolve(imagePath).resolve(image.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(files)) {
            os.write(image.getBytes());
        }

        post.setPath(domain + imagePath.resolve(image.getOriginalFilename()));

        return postRepository.save(post);
    }
}
