package com.example.myblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer blogId;
    private Integer commentId;
    private String comment;
    private LocalDateTime createTime;
    private String username;
}
