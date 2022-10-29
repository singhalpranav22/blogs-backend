package com.pranav.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private String message;
    public ApiResponse(String message){
        this.message = message;
    }
}
