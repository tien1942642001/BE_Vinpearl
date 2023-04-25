package dev.kienntt.demo.BE_Vinpearl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "post_id")
    private Long postId;

    @ManyToOne
    @JoinColumn(name="post_id", nullable=false, insertable = false, updatable = false)
    @JsonIgnore
    private Post post;

    @Column(name = "customer_id")
    private Long customerId;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false, insertable = false, updatable = false)
    private Customer customer;

    @OneToMany(mappedBy = "comment", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnoreProperties("roomType")
    private Set<CommentChildren> commentChildrens;
}
