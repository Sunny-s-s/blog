package com.example.blog.controller.api;

import com.example.blog.common.ApiResponse;
import com.example.blog.entity.Article;
import com.example.blog.Mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ArticleController {
    
    @Autowired
    private ArticleMapper articleMapper;

    @PostMapping("/save")
    public ApiResponse saveArticle(@RequestBody Article article) {
        try {
            if (article.getId() == null) {
                // 新文章
                article.setCreateTime(new Date());
                article.setUpdateTime(new Date());
                articleMapper.insert(article);
            } else {
                // 更新文章
                article.setUpdateTime(new Date());
                articleMapper.updateById(article);
            }
            return ApiResponse.success("保存成功", article);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error(500, "保存失败: " + e.getMessage());
        }
    }

    @GetMapping("/list")
    public ApiResponse getArticles() {
        try {
            System.out.println("开始获取文章列表");
            List<Article> articles = articleMapper.selectAllArticles(); // 使用自定义查询方法
            System.out.println("查询到的文章数量: " + (articles != null ? articles.size() : 0));
            System.out.println("查询到的文章: " + articles);
            return ApiResponse.success("获取成功", articles);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("获取文章列表错误: " + e.getMessage());
            return ApiResponse.error(500, "获取文章列表失败: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ApiResponse getArticle(@PathVariable Integer id) {
        Article article = articleMapper.selectById(id);
        if (article == null) {
            return ApiResponse.error(404, "文章不存在");
        }
        return ApiResponse.success("获取成功", article);
    }
}
