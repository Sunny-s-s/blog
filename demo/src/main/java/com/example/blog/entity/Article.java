package com.example.blog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("articles") // 指定表名
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private Integer id;
    private String title;
    private String content;
    private String category;
    private Integer userId;
    private Date createTime;
    private Date updateTime;
    private Integer status; // 0:草稿 1:已发布
}