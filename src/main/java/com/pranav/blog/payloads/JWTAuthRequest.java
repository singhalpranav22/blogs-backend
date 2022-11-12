package com.pranav.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JWTAuthRequest {
    private String username;
    private String password;
}
