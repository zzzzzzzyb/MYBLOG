package com.example.myblog.mapper;

import com.example.myblog.pojo.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into comment(blog_id, comment,username) VALUES(#{blogId},#{comment},#{username})")
    Integer CreateComment(Integer blogId, String comment, String username);

    @Select("select * from comment where blog_id=#{blogId}")
    List<Comment> SelectComment(Integer blogId);
}
