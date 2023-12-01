package com.example.myblog.service.impl;

import com.example.myblog.mapper.CommentMapper;
import com.example.myblog.pojo.Comment;
import com.example.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public Integer CreateComment(Integer blogId, String comment, String username) {
        return commentMapper.CreateComment(blogId,comment,username);
    }

    @Override
    public List<Comment> SelectComment(Integer blog_id) {
        return commentMapper.SelectComment(blog_id);
    }
}
