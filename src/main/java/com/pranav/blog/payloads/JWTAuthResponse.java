package com.pranav.blog.payloads;

import lombok.Data;

@Data
public class JWTAuthResponse  {
    private String tokenType = "Bearer";
    private String accessToken;
    public JWTAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
