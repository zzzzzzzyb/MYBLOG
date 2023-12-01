package com.example.myblog.service;

import com.example.myblog.pojo.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> allBlog(String username);

    Blog ShowBlog(Integer id);

    void DeleteBlog(Integer id);

    Integer CreateBlog(String title, String username);

    Integer CreateId(String title);

    String DeleteId(Integer id);

    void SaveBlog(Integer id, String text);
    Integer LoginBlog(String username, String password);

    Integer CreateUser(String username, String password);

    List<Blog> BlogAll();
}
