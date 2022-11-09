package com.pranav.blog.controllers;

import com.pranav.blog.payloads.CommentDto;
import com.pranav.blog.payloads.PostDto;
import com.pranav.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/post/{postId}/user/{userId}")
    ResponseEntity<CommentDto> getAllComments(
            @RequestBody CommentDto commentDto,
            @PathVariable("postId") Integer postId,
            @PathVariable("userId") Integer userId){
        CommentDto addedComment = commentService.addComment(commentDto,postId,userId);
        return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    ResponseEntity<?> deleteComment(@PathVariable("commentId") Integer commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(Map.of("message", String.format("Comment with id = %s deleted successfully", commentId)),HttpStatus.OK);
    }
}
