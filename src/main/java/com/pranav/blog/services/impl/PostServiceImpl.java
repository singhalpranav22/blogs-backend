package com.pranav.blog.services.impl;

import com.pranav.blog.entities.Category;
import com.pranav.blog.entities.Post;
import com.pranav.blog.entities.User;
import com.pranav.blog.exceptions.ResourceNotFoundException;
import com.pranav.blog.payloads.CategoryDto;
import com.pranav.blog.payloads.PostDto;
import com.pranav.blog.payloads.UserDto;
import com.pranav.blog.repositories.CategoryRepo;
import com.pranav.blog.repositories.PostRepo;
import com.pranav.blog.repositories.UserRepo;
import com.pranav.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepo postRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public PostDto addPost(PostDto postDto,Integer userId,Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user","UserId",userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category","CategoryId",categoryId));
        if(postDto.getCoverImage()==null)
            postDto.setCoverImage("https://images.unsplash.com/photo-1504805572947-34fad45aed93?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80");
        postDto.setPostedOn(new java.util.Date());
        postDto.setUpdatedOn(new java.util.Date());
        Post post = modelMapper.map(postDto, Post.class);
        post.setUser(user);
        post.setCategory(category);
        postDto.setUser(modelMapper.map(user, UserDto.class));
        postDto.setCategory(modelMapper.map(category,CategoryDto.class));
        postRepo.save(post);
        return postDto;
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new com.pranav.blog.exceptions.ResourceNotFoundException("post", "postId", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        if(!postDto.getCoverImage().isBlank())
            post.setCoverImage(postDto.getCoverImage());
        post.setUpdatedOn(new java.util.Date());
        PostDto updatedPost = modelMapper.map(postRepo.save(post), PostDto.class);
        return updatedPost;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new com.pranav.blog.exceptions.ResourceNotFoundException("post", "postId", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        return null;
    }

    @Override
    public List<PostDto> getPostsByKeyword(String keyword) {
        return null;
    }
}


