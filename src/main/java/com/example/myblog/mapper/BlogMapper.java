package com.example.myblog.mapper;

import com.example.myblog.pojo.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {
    @Select("select * from blogs where username=#{username}")
    List<Blog> allBlog(String username);

    @Select("select * from blogs where id=#{id}")
    Blog ShowBlog(Integer id);
    @Delete("delete from blogs where id=#{id}")
    void DeleteBlog(Integer id);
    @Insert("insert blogs(title,username) values(#{title},#{username})")
    Integer CreateBlog(String title, String username);
    @Select("select id from blogs where create_time in (select max(create_time) from blogs);")
    Integer CreateId(String title);
    @Select("select title from blogs where id=#{id}")
    String DeleteId(Integer id);
    @Update("update blogs set text=#{text},update_time=now() where id=#{id}")
    void SaveBlog(Integer id, String text);
    @Select("select password from user where username=#{username}")
    String LoginBlog(String username, String password);
    @Insert("insert into user values(#{username},#{password})")
    Integer CreateUser(String username, String password);
    @Select("SELECT * from blogs order by update_time desc ")
    List<Blog> BlogAll();
}
