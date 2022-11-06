package com.pranav.blog.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;
    @Column(name="post_title",nullable = false,length = 100)
    private String title;
    @Column(name="post_content",nullable = false,length = 100000)
    private String content;
    private String coverImage;
    private Date postedOn;
    private Date updatedOn;
    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;

}
