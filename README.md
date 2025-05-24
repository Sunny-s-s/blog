MyBlog - 现代化博客系统
一个基于 Vue 3 + Spring Boot 的全栈博客系统，提供流畅的写作和阅读体验。
功能特点
🎨 现代化 UI 设计
📱 响应式布局，支持移动设备
🔐 JWT 认证和授权
✍️ Markdown 文章编辑
🔍 文章搜索和分类
👥 用户管理系统
📊 阅读时间估算
🌈 个性化文章封面
技术栈
前端
Vue 3
Vite
Vue Router
Element Plus
Axios
后端
Spring Boot
MyBatis-Plus
MySQL
JWT
快速开始
前端启动
cd my-vue-app
npm install
npm run dev
后端启动
cd demo
mvn spring-boot:run
API 文档
详细的 API 文档请查看 api.md
基础信息
基础路径: /api/v1
响应格式: JSON
认证方式: JWT Token
编码格式: UTF-8
项目结构
├── my-vue-app/          # 前端项目
│   ├── src/
│   │   ├── api/        # API 接口
│   │   ├── components/ # 组件
│   │   ├── views/      # 页面
│   │   ├── router/     # 路由配置
│   │   └── utils/      # 工具函数
│   └── ...
│
├── demo/                # 后端项目
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   └── test/
│   └── ...
功能预览
用户认证 (登录/注册)
文章管理 (创建/编辑/删除)
文章列表展示
文章详情阅读
个人信息管理
贡献指南
Fork 本仓库
创建新的功能分支 (git checkout -b feature/AmazingFeature)
提交更改 (git commit -m 'Add some AmazingFeature')
推送到分支 (git push origin feature/AmazingFeature)
创建 Pull Request
许可证
本项目采用 MIT 许可证 - 查看 LICENSE 文件了解详细信息
联系方式
Email: example@example.com
GitHub: github.com/example
微信: myblog-tech
致谢
感谢所有为本项目做出贡献的开发者！

⭐️ 如果这个项目对你有帮助，欢迎 star 支持！
