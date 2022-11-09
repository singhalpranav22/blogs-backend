package com.pranav.blog.services.impl;

import com.pranav.blog.entities.Comment;
import com.pranav.blog.entities.Post;
import com.pranav.blog.entities.User;
import com.pranav.blog.exceptions.ResourceNotFoundException;
import com.pranav.blog.payloads.CommentDto;
import com.pranav.blog.payloads.PostDto;
import com.pranav.blog.payloads.UserDto;
import com.pranav.blog.repositories.CommentRepo;
import com.pranav.blog.repositories.PostRepo;
import com.pranav.blog.repositories.UserRepo;
import com.pranav.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public CommentDto addComment(CommentDto commentDto, Integer postId, Integer userId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post","PostId",postId));
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user","UserId",userId));
        commentDto.setPostDto(modelMapper.map(post, PostDto.class));
        commentDto.setUserDto(modelMapper.map(user, UserDto.class));
        Comment addedComment = commentRepo.save(modelMapper.map(commentDto,Comment.class));
        return modelMapper.map(addedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment","CommentId",commentId));
        commentRepo.deleteById(commentId);
    }
}
