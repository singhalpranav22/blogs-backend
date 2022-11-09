package com.pranav.blog.payloads;

import com.pranav.blog.entities.Post;
import com.pranav.blog.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private int id;
    private String comment;
    private PostDto postDto;
    private UserDto userDto;
}
