package com.pranav.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;

    @NotEmpty @Size(min=3,max = 90,message = "Name should have >=3 and <=90 characters.")
    private String name;
    @NotEmpty(message = "Email field cannot be empty.") @Email @Size(max = 90, message = "Email can be at most 90 characters long.")
    private String email;
    @NotEmpty @Size(min = 5,max=90, message = "Password should have >=5 and <=90 characters.")
    private String password;
    @NotEmpty(message = "About field cannot be empty.")
    @Size(max = 100,message = "About can be at most 100 characters long.")
    private String about;
    private Set<CommentDto> comments = new HashSet<>();
}
