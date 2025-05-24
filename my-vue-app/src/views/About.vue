<template>
    <div class="blog-container">
        <header class="header">
            <div class="logo">MyBlog</div>
            <nav class="nav-menu">
                <router-link to="/home" class="nav-item">首页</router-link>
                <router-link to="/articles" class="nav-item">文章</router-link>
                <router-link to="/about" class="nav-item">关于</router-link>
                <button @click="goToLogin" class="login-btn">登出</button>
            </nav>
        </header>

        <main class="main-content">
            <section class="hero-section">
                <h1>关于我们</h1>
                <p>了解更多关于我们的故事</p>
            </section>

            <section class="about-content">
                <div class="content-card">
                    <h2>博客介绍</h2>
                    <p>这是一个基于 Vue 3 和 Spring Boot 构建的博客系统，致力于为用户提供优质的写作和阅读体验。</p>
                </div>

                <div class="content-card">
                    <h2>技术栈</h2>
                    <ul>
                        <li>前端：Vue 3 + Vite + Element Plus</li>
                        <li>后端：Spring Boot + MyBatis-Plus</li>
                        <li>数据库：MySQL</li>
                        <li>部署：Docker</li>
                    </ul>
                </div>

                <div class="content-card">
                    <h2>联系方式</h2>
                    <p><strong>Email:</strong> example@example.com</p>
                    <p><strong>GitHub:</strong> github.com/example</p>
                    <p><strong>微信:</strong> myblog-tech</p>
                </div>
            </section>
        </main>

        <footer class="footer">
            <p>&copy; 2024 MyBlog. All rights reserved.</p>
        </footer>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const goToLogin = () => {
    router.push('/login')
}
</script>

<style scoped>
.blog-container {
    min-height: 100vh;
    display: flex;
    flex-direction: column;
}

.header {
    background-color: #ffffff;
    box-shadow: 0 2px 8px rgba(0,0,0,0.1);
    padding: 1rem 2rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
    position: sticky;
    top: 0;
    z-index: 100;
}

.logo {
    font-size: 1.5rem;
    font-weight: bold;
    color: #2c3e50;
}

.nav-menu {
    display: flex;
    gap: 2rem;
    align-items: center;
}

.nav-item {
    text-decoration: none;
    color: #2c3e50;
    font-weight: 500;
    transition: color 0.3s;
}

.nav-item:hover {
    color: #42b883;
}

.login-btn {
    background-color: #42b883;
    color: white;
    border: none;
    padding: 0.5rem 1rem;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.login-btn:hover {
    background-color: #3aa876;
}

.main-content {
    flex: 1;
    padding: 2rem;
}

.hero-section {
    text-align: center;
    padding: 4rem 0;
    background: linear-gradient(135deg, #42b883 0%, #347474 100%);
    color: white;
    border-radius: 8px;
    margin-bottom: 2rem;
}

.hero-section h1 {
    font-size: 2.5rem;
    margin-bottom: 1rem;
}

.about-content {
    max-width: 800px;
    margin: 0 auto;
    display: grid;
    gap: 2rem;
}

.content-card {
    background: white;
    border-radius: 8px;
    padding: 2rem;
    box-shadow: 0 2px 12px rgba(0,0,0,0.1);
    transition: transform 0.3s;
}

.content-card:hover {
    transform: translateY(-5px);
}

.content-card h2 {
    color: #2c3e50;
    margin-bottom: 1rem;
    font-size: 1.5rem;
}

.content-card p {
    color: #666;
    line-height: 1.6;
}

.content-card ul {
    list-style: none;
    padding: 0;
}

.content-card li {
    color: #666;
    padding: 0.5rem 0;
    border-bottom: 1px solid #eee;
}

.content-card li:last-child {
    border-bottom: none;
}

.footer {
    background-color: #2c3e50;
    color: white;
    text-align: center;
    padding: 1.5rem;
    margin-top: auto;
}

@media (max-width: 768px) {
    .nav-menu {
        gap: 1rem;
    }

    .hero-section {
        padding: 2rem 0;
    }

    .about-content {
        padding: 1rem;
    }
}
</style>