package com.example.myblog.controller;

import com.example.myblog.pojo.Blog;
import com.example.myblog.pojo.Comment;
import com.example.myblog.pojo.Result;
import com.example.myblog.service.BlogService;
import com.example.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;
    @CrossOrigin
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public Result allBlog(@RequestParam("username") String username){
        List<Blog> AllBlog=blogService.allBlog(username);
        for (Blog i :AllBlog)
            i.setText("");
        return Result.success(AllBlog);
    }
    @CrossOrigin
    @RequestMapping(value = "/home",method = RequestMethod.POST)
    public Integer CreateBlog(@RequestParam("title") String title,@RequestParam("username") String username){
        blogService.CreateBlog(title,username);
        return blogService.CreateId(title);
    }

    @CrossOrigin
    @DeleteMapping("/home")
    public Result DeleteBlog(@RequestParam("id") Integer id){
        String title=blogService.DeleteId(id);
        blogService.DeleteBlog(id);
        return Result.success(title);
    }

    @CrossOrigin
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public Result ShowBlog(@RequestParam("id") Integer id){
        Blog showBlog=blogService.ShowBlog(id);
        List<Comment> comments=commentService.SelectComment(id);
        List answer=new ArrayList();
        answer.add(showBlog);
        answer.add(comments);
        return Result.success(answer);
    }

    @CrossOrigin
    @RequestMapping(value = "/show",method = RequestMethod.POST)
    public Result CreateComment(@RequestParam("comment") String comment,@RequestParam("blog_id") Integer blog_id,
                                @RequestParam("username") String username){
        return Result.success(commentService.CreateComment(blog_id,comment,username));
    }

    @CrossOrigin
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public Result SaveBlog(@RequestParam("id") Integer id,@RequestParam("text") String text){
        blogService.SaveBlog(id,text);
        return Result.success();
    }

    @CrossOrigin
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public Result Show4Edit(@RequestParam("id") Integer id){
        return Result.success(blogService.ShowBlog(id));
    }

    @CrossOrigin
    @RequestMapping(value = "/",method=RequestMethod.GET)
    public Result LoginBlog(@RequestParam("username") String username, @RequestParam("password") String password){
        return Result.success(blogService.LoginBlog(username,password));
    }

    @CrossOrigin
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Result CreateUser(@RequestParam("username") String username,@RequestParam("password") String password){
        return Result.success(blogService.CreateUser(username,password));
    }

    @CrossOrigin
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Result BlogAll(){
        return Result.success(blogService.BlogAll());
    }
}
