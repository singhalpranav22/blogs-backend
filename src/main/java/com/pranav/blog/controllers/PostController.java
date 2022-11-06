package com.pranav.blog.controllers;

import com.pranav.blog.payloads.PostDto;
import com.pranav.blog.payloads.PostsResponse;
import com.pranav.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
        PostDto addedPost = postService.addPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(addedPost, HttpStatus.CREATED);
    }

    @GetMapping("/posts/")
    public ResponseEntity<PostsResponse> getAllPosts(
            @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(defaultValue = "5" , required = false) Integer pageSize,
            @RequestParam(defaultValue = "postId", required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDirection) {
        return new ResponseEntity<>(postService.getAllPosts(pageNumber,pageSize,sortBy,sortDirection),HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        return new ResponseEntity<>(postService.getPostById(postId),HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
        return new ResponseEntity<>(postService.getPostsByUser(userId),HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
        return new ResponseEntity<>(postService.getPostsByCategory(categoryId),HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(Map.of("message", String.format("Post with id = %s deleted successfully", postId)),HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        return new ResponseEntity<>(postService.updatePost(postDto,postId),HttpStatus.OK);
    }
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> getPostsByKeyword(@PathVariable String keyword){
        return new ResponseEntity<>(postService.getPostsByKeyword(keyword),HttpStatus.OK);
    }
}
