package com.pranav.blog.services;

import com.pranav.blog.payloads.CommentDto;

import java.util.List;

public interface CommentService {
   public CommentDto addComment(CommentDto commentDto,Integer postId,Integer userId);
   public void deleteComment(Integer commentId);
}
