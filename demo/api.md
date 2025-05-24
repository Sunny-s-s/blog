# 博客系统 API 文档

## 基础信息
- **基础路径**: `/api/v1`
- **响应格式**: JSON
- **认证方式**: JWT Token
- **编码格式**: UTF-8
- **版本**: 1.0.0

## 通用响应格式
```json
{
  "code": 200,      // 状态码
  "message": "",    // 提示信息
  "data": null      // 响应数据
}
```

## API 接口列表

### 1. 用户认证接口

#### 1.1 用户注册
- **接口**: `POST /api/v1/auth/register`
- **描述**: 新用户注册
- **请求参数**:
```json
{
  "username": "string",  // 用户名
  "password": "string",  // 密码
  "id": "integer"       // 用户ID
}
```
- **响应示例**:
```json
{
  "code": 200,
  "message": "注册成功",
  "data": {
    "id": 1,
    "username": "test_user",
    "token": "eyJhbGciOiJ..."
  }
}
```

#### 1.2 用户登录
- **接口**: `POST /api/v1/auth/login`
- **描述**: 用户登录认证
- **请求参数**:
```json
{
  "username": "string",  // 用户名
  "password": "string"   // 密码
}
```
- **响应示例**:
```json
{
  "code": 200,
  "message": "登录成功",
  "data": {
    "token": "eyJhbGciOiJ...",
    "user": {
      "id": 1,
      "username": "test_user"
    }
  }
}
```

#### 1.3 用户登出
- **接口**: `POST /api/v1/auth/logout`
- **描述**: 用户退出登录
- **请求头**:
  - `Authorization: Bearer <token>`
- **响应示例**:
```json
{
  "code": 200,
  "message": "登出成功"
}
```

### 2. 用户管理接口

#### 2.1 获取用户列表
- **接口**: `GET /api/v1/users`
- **描述**: 获取所有用户列表
- **请求头**:
  - `Authorization: Bearer <token>`
- **响应示例**:
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "total": 100,
    "list": [
      {
        "id": 1,
        "username": "test_user"
      }
    ]
  }
}
```

## 错误码说明

| 错误码 | 说明 |
|--------|------|
| 200 | 请求成功 |
| 400 | 请求参数错误 |
| 401 | 未授权或token失效 |
| 403 | 权限不足 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |

## 注意事项

1. 所有请求都需要在 header 中设置:
   ```
   Content-Type: application/json
   ```

2. 除了登录和注册接口，其他接口都需要在 header 中携带 token:
   ```
   Authorization: Bearer <token>
   ```

3. 密码传输需要进行加密处理

4. 所有接口支持 CORS 跨域访问

## 更新日志

### v1.0.0 (2025-04-14)
- 初始版本
- 实现用户基础功能
- 添加认证相关接口