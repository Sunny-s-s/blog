package com.example.blog.controller.api;

import com.example.blog.common.ApiResponse;
import com.example.blog.entity.User;
import com.example.blog.Mapper.UserMapper;
import com.example.blog.dto.LoginResponse;
import com.example.blog.dto.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {
    
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/auth/register")
    public ApiResponse register(@RequestBody User user) {
        User existingUser = userMapper.findByUsername(user.getUsername());
        if (existingUser != null) {
            return ApiResponse.error(400, "该用户已存在");
        }

        userMapper.insert(user);
        return ApiResponse.success("注册成功", user);
    }

    @PostMapping("/auth/login")
public ApiResponse login(@RequestBody User loginUser, HttpSession session) {
    try {
        // 添加日志
        System.out.println("收到登录请求: " + loginUser);
        
        // 检查用户名是否为空
        if (loginUser.getUsername() == null || loginUser.getPassword() == null) {
            return ApiResponse.error(400, "用户名或密码不能为空");
        }

        User user = userMapper.findByUsername(loginUser.getUsername());
        System.out.println("查询到的用户: " + user);  // 添加日志

        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            session.setAttribute("user", user);
            String token = "eyJhbGciOiJ..."; // TODO: 实现真实的JWT token生成
            return ApiResponse.success("登录成功", new LoginResponse(token, user));
        }
        
        return ApiResponse.error(401, "用户名或密码错误");
    } catch (Exception e) {
        // 添加错误日志
        e.printStackTrace();
        return ApiResponse.error(500, "服务器错误: " + e.getMessage());
    }
}

    @PostMapping("/auth/logout")
    public ApiResponse logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return ApiResponse.success("登出成功");
    }

    @GetMapping("/users")
    public ApiResponse getAllUsers() {
        List<User> users = userMapper.selectList(null);
        return ApiResponse.success("获取成功", new PageResponse<>(users.size(), users));
    }
}