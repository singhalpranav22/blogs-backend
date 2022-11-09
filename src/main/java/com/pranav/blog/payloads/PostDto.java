package com.pranav.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    @NotEmpty  @Size(min=3,max = 90,message = "title should have >=3 and <=90 characters.")
    private String title;
    @NotEmpty  @Size(min=3,max = 100000,message = "content should have >=3 and <=100000 characters.")
    private String content;
    private String coverImage;
    private Date postedOn;
    private Date updatedOn;
    private UserDto user;
    private CategoryDto category;
    private Set<CommentDto> comments = new HashSet<>();
}
