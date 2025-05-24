<template>
    <div class="blog-container">
        <header class="header">
            <div class="logo">MyBlog</div>
            <nav class="nav-menu">
                <router-link to="/home" class="nav-item">首页</router-link>
                <router-link to="/articles" class="nav-item">文章</router-link>
                <router-link to="/about" class="nav-item">关于</router-link>
                <button @click="goToLogin" class="login-btn">登出</button>
                <button @click="createNewPost" class="new-post-btn">新建文章</button>
            </nav>
        </header>

        <main class="main-content">
            <section class="hero-section">
                <div class="hero-content">
                    <h1>欢迎来到我的博客</h1>
                    <p class="hero-subtitle">分享技术，记录生活</p>
                    <router-link to="/articles" class="explore-btn">
                        探索更多 <i class="el-icon-arrow-right"></i>
                    </router-link>
                </div>
            </section>

            <section class="featured-posts">
                <div class="section-header">
                    <h2>精选文章</h2>
                    <router-link to="/articles" class="view-all">
                        查看全部 <i class="el-icon-arrow-right"></i>
                    </router-link>
                </div>
                <div class="featured-grid">
                    <!-- 大卡片展示最新文章 -->
                    <div v-if="featuredPosts[0]" 
                         class="featured-main"
                         @click="goToArticle(featuredPosts[0])">
                        <div class="featured-content">
                            <div class="category-tag">{{ featuredPosts[0].category }}</div>
                            <h3>{{ featuredPosts[0].title }}</h3>
                            <p>{{ featuredPosts[0].excerpt }}</p>
                            <div class="post-meta">
                                <span>
                                    <i class="el-icon-date"></i>
                                    {{ featuredPosts[0].date }}
                                </span>
                                <span>
                                    <i class="el-icon-reading"></i>
                                    {{ featuredPosts[0].readTime }} 分钟阅读
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="featured-side">
                        <div v-for="post in featuredPosts.slice(1, 4)" 
                             :key="post.id" 
                             class="side-card"
                             @click="goToArticle(post)">
                            <div class="side-content">
                                <div class="category-tag">{{ post.category }}</div>
                                <h4>{{ post.title }}</h4>
                                <div class="post-meta">
                                    <span>{{ post.date }}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>

        <footer class="footer">
            <p>&copy; 2024 MyBlog. All rights reserved.</p>
        </footer>
    </div>
</template>

<script setup>
import { ref,onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { getArticleList } from '@/api/article'

const goToArticle = (post) => {
    router.push({
        name: 'ArticleDetail',
        params: { id: post.id }
    })
}
// 获取文章列表
const fetchArticles = async () => {
    try {
        const res = await getArticleList()
        if (res.code === 200) {
            // 将后端返回的文章数据转换为前端需要的格式
            featuredPosts.value = res.data.map(article => ({
                id: article.id,
                title: article.title,
                excerpt: article.content.substring(0, 100) + '...', // 截取前100个字符作为摘要
                image: '/images/default.jpg', // 可以根据文章分类设置不同的默认图片
                date: new Date(article.createTime).toLocaleDateString(),
                readTime: Math.ceil(article.content.length / 400), // 粗略估计阅读时间
                category: article.category
            }))
        }
    } catch (error) {
        console.error('获取文章列表失败:', error)
        ElMessage.error('获取文章列表失败')
    }
}
// 在组件挂载时获取文章列表
onMounted(() => {
    fetchArticles()
})

const router = useRouter()
const createNewPost = () => {
    router.push({
        name: 'Edit',
        params: { 
            id: 'new',  // 标识这是新文章
            isNew: true // 用于区分新建还是编辑
        }
    })
}
const goToLogin = () => {
    router.push('/login')
}
const featuredPosts = ref([
    {
        id: 1,
        title: 'Vue 3 组合式 API 实践',
        excerpt: '探索 Vue 3 组合式 API 的最佳实践和使用技巧...',
        image: '/images/vue3.jpg',
        date: '2024-04-14',
        readTime: 5
    },
    {
        id: 2,
        title: 'TypeScript 入门指南',
        excerpt: '从零开始学习 TypeScript，掌握类型系统...',
        image: '/images/typescript.jpg',
        date: '2024-04-13',
        readTime: 8
    },
    {
        id: 3,
        title: '现代化前端工程化实践',
        excerpt: '探讨现代前端开发中的工程化解决方案...',
        image: '/images/frontend.jpg',
        date: '2024-04-12',
        readTime: 6
    }
])
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

.featured-posts {
    margin: 2rem 0;
}

.featured-posts h2 {
    color: #2c3e50;
    margin-bottom: 1.5rem;
}

.post-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 2rem;
}

.post-card {
    background: white;
    border-radius: 8px;
    overflow: hidden;
    box-shadow: 0 2px 12px rgba(0,0,0,0.1);
    transition: transform 0.3s;
}

.post-card:hover {
    transform: translateY(-5px);
}

.post-image {
    width: 100%;
    height: 200px;
    object-fit: cover;
}

.post-content {
    padding: 1.5rem;
}

.post-content h3 {
    color: #2c3e50;
    margin-bottom: 0.5rem;
}

.post-meta {
    margin-top: 1rem;
    display: flex;
    justify-content: space-between;
    color: #666;
    font-size: 0.9rem;
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

    .post-grid {
        grid-template-columns: 1fr;
    }
}
.blog-header {
    padding: 1rem 2rem;
    background: white;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    display: flex;
    justify-content: flex-end;
    margin-bottom: 2rem;
}

.new-post-btn {
    padding: 0.5rem 1.5rem;
    background: #42b883;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    display: flex;
    align-items: center;
    gap: 0.5rem;
    font-size: 1rem;
    transition: background-color 0.3s;
}

.new-post-btn:hover {
    background: #3aa876;
}

.new-post-btn i {
    font-size: 1.2rem;
}

.hero-section {
    position: relative;
    padding: 6rem 2rem;
    background: linear-gradient(135deg, #42b883 0%, #347474 100%);
    color: white;
    border-radius: 12px;
    margin-bottom: 3rem;
    overflow: hidden;
}

.hero-section::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: url('/images/pattern.svg') center/cover;
    opacity: 0.1;
}

.hero-content {
    position: relative;
    max-width: 800px;
    margin: 0 auto;
    text-align: center;
}

.hero-subtitle {
    font-size: 1.25rem;
    margin: 1.5rem 0;
    opacity: 0.9;
}

.explore-btn {
    display: inline-flex;
    align-items: center;
    gap: 0.5rem;
    background: white;
    color: #42b883;
    padding: 0.75rem 2rem;
    border-radius: 25px;
    text-decoration: none;
    font-weight: 500;
    transition: all 0.3s;
}
.explore-btn:hover {
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
}

.section-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 2rem;
}

.view-all {
    display: flex;
    align-items: center;
    gap: 0.5rem;
    color: #42b883;
    text-decoration: none;
    font-weight: 500;
}

.featured-grid {
    display: grid;
    grid-template-columns: 1.5fr 1fr;
    gap: 2rem;
}
.featured-main {
    background: white;
    border-radius: 12px;
    overflow: hidden;
    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    cursor: pointer;
    transition: transform 0.3s;
}

.featured-main:hover {
    transform: translateY(-5px);
}

.featured-content {
    padding: 2rem;
}
.category-tag {
    display: inline-block;
    padding: 0.25rem 1rem;
    background: #f0f9f6;
    color: #42b883;
    border-radius: 15px;
    font-size: 0.875rem;
    margin-bottom: 1rem;
}

.featured-side {
    display: flex;
    flex-direction: column;
    gap: 1rem;
}

.side-card {
    background: white;
    border-radius: 8px;
    padding: 1.5rem;
    box-shadow: 0 2px 8px rgba(0,0,0,0.1);
    cursor: pointer;
    transition: transform 0.3s;
}
.side-card:hover {
    transform: translateY(-3px);
}

.side-card h4 {
    margin: 0.5rem 0;
    color: #2c3e50;
    font-size: 1.1rem;
}

@media (max-width: 968px) {
    .featured-grid {
        grid-template-columns: 1fr;
    }

    .hero-section {
        padding: 4rem 1rem;
    }
}
</style>