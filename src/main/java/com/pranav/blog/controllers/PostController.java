package com.pranav.blog.controllers;

import com.pranav.blog.payloads.PostDto;
import com.pranav.blog.payloads.PostsResponse;
import com.pranav.blog.services.FileService;
import com.pranav.blog.services.PostService;
import com.sun.net.httpserver.HttpServer;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;

    @Value("${project.images}")
    private String path;

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

    @PostMapping("/posts/image/upload/{postId}")
     public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable Integer postId) throws IOException {
        PostDto postDto = postService.getPostById(postId);
        String fileName = fileService.uploadFile(path, file);
        postDto.setCoverImage(fileName);
        PostDto updatedPost = postService.updatePost(postDto, postId);

        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }
    // Method to serve files
    @GetMapping(value = "/posts/image/download/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
         InputStream resource = fileService.downloadFile(path,imageName);
         response.setContentType(MediaType.IMAGE_JPEG_VALUE);
         StreamUtils.copy(resource,response.getOutputStream());
    }
}
