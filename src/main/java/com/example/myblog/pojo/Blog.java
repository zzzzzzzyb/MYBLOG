package com.example.myblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    private Integer id;
    private String title;
    private String text;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String username;
}
