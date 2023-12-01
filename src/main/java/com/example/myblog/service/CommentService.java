package com.example.myblog.service;

import com.example.myblog.pojo.Comment;

import java.util.List;

public interface CommentService {
    Integer CreateComment(Integer blogId, String comment, String username);

    List<Comment> SelectComment(Integer blog_id);
}
