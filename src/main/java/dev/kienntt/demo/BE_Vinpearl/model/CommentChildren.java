package dev.kienntt.demo.BE_Vinpearl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comment_children")
@Getter
@Setter
public class CommentChildren {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne()
    @JoinColumn(name = "comment_id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private Comment comment;

    @Column(name = "content")
    private String content;

}
