package dev.kienntt.demo.BE_Vinpearl.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
public class PostListDto {
    private long id;
    private String name;
    private String path;

    private String publishedAt;

    private long countLike;

    private long countComment;
}
