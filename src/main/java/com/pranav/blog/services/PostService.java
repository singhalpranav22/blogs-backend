package com.pranav.blog.services;

import com.pranav.blog.payloads.PostDto;

import java.util.List;

public interface PostService {
    PostDto addPost(PostDto postDto,Integer userId,Integer categoryId);
    PostDto updatePost(PostDto postDto,Integer postId);
    PostDto getPostById(Integer postId);
    List<PostDto> getAllPosts();
    void deletePost(Integer postId);
    // get posts by category
    List<PostDto> getPostsByCategory(Integer categoryId);
    // get posts by user
    List<PostDto> getPostsByUser(Integer userId);
    // get posts by keyword
    List<PostDto> getPostsByKeyword(String keyword);
}
