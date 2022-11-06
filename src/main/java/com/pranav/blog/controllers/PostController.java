package com.pranav.blog.controllers;

import com.pranav.blog.payloads.PostDto;
import com.pranav.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
        PostDto addedPost = postService.addPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(addedPost, HttpStatus.CREATED);
    }
}
