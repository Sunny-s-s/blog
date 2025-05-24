package com.example.blog.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.Article;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {
    @Select("SELECT * FROM articles ORDER BY create_time DESC")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "content", column = "content"),
        @Result(property = "category", column = "category"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time"),
        @Result(property = "status", column = "status")
    })
    List<Article> selectAllArticles();
    
}