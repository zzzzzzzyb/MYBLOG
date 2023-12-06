package com.example.myblog.service.impl;

import com.example.myblog.mapper.BlogMapper;
import com.example.myblog.pojo.Blog;
import com.example.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Override
    public List<Blog> allBlog(String username) {
        return blogMapper.allBlog(username);
    }

    @Override
    public Blog ShowBlog(Integer id) {
        return blogMapper.ShowBlog(id);
    }

    @Override
    public void DeleteBlog(Integer id) {
        blogMapper.DeleteBlog(id);
    }

    @Override
    public Integer CreateBlog(String title, String username) {
        return blogMapper.CreateBlog(title,username);
    }

    @Override
    public Integer CreateId(String title) {
        return blogMapper.CreateId(title);
    }

    @Override
    public String DeleteId(Integer id) {
        return blogMapper.DeleteId(id);
    }

    @Override
    public void SaveBlog(Integer id, String text) {
        blogMapper.SaveBlog(id,text);
    }

    @Override
    public Integer LoginBlog(String username, String password) {
        return blogMapper.LoginBlog(username,password).equals(password)?1:0;
    }

    @Override
    public Integer CreateUser(String username, String password) {
        return blogMapper.CreateUser(username,password);
    }

    @Override
    public List<Blog> BlogAll() {
        return blogMapper.BlogAll();
    }
}
