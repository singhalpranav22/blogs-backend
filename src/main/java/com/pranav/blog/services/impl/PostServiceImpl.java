package com.pranav.blog.services.impl;

import com.pranav.blog.entities.Category;
import com.pranav.blog.entities.Post;
import com.pranav.blog.entities.User;
import com.pranav.blog.exceptions.ResourceNotFoundException;
import com.pranav.blog.payloads.CategoryDto;
import com.pranav.blog.payloads.PostDto;
import com.pranav.blog.payloads.PostsResponse;
import com.pranav.blog.payloads.UserDto;
import com.pranav.blog.repositories.CategoryRepo;
import com.pranav.blog.repositories.PostRepo;
import com.pranav.blog.repositories.UserRepo;
import com.pranav.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Post addedPost = postRepo.save(post);
        return modelMapper.map(addedPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new com.pranav.blog.exceptions.ResourceNotFoundException("post", "postId", postId));
        if(postDto.getTitle()!=null)
            post.setTitle(postDto.getTitle());
        if(postDto.getContent()!=null)
            post.setContent(postDto.getContent());
        if(postDto.getCoverImage()!=null)
            post.setCoverImage(postDto.getCoverImage());
        post.setUpdatedOn(new java.util.Date());
        PostDto updatedPost = modelMapper.map(postRepo.save(post), PostDto.class);
        postRepo.save(post);
        return updatedPost;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new com.pranav.blog.exceptions.ResourceNotFoundException("post", "postId", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostsResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDirection) {
        Pageable pageable;
        if(sortDirection.equals("asc")) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        }
        Page<Post> postsPages = postRepo.findAll(pageable);
        List<Post>  posts = postsPages.getContent();
        // convert Post list to PostDto list with stream and modelmapper
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
        PostsResponse postsResponse = new PostsResponse();
        postsResponse.setPosts(postDtos);
        postsResponse.setTotalPages(postsPages.getTotalPages());
        postsResponse.setTotalElements(postsPages.getTotalElements());
        postsResponse.setPageNumber(postsPages.getNumber());
        postsResponse.setPageSize(postsPages.getSize());
        postsResponse.setHasNextPage(postsPages.hasNext());
        postsResponse.setHasPreviousPage(postsPages.hasPrevious());
        postsResponse.setIsLastPage(postsPages.isLast());
        postsResponse.setIsFirstPage(postsPages.isFirst());
        return postsResponse;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new com.pranav.blog.exceptions.ResourceNotFoundException("post", "postId", postId));
        postRepo.delete(post);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category","CategoryId",categoryId));
        List<Post> posts = postRepo.findByCategory(category);
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user","UserId",userId));
        List<Post> posts = postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByKeyword(String keyword) {
        List<Post> posts = postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
        return postDtos;
    }
}


