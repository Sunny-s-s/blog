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
                <h1>文章列表</h1>
                <p>在这里查看所有精彩内容</p>
            </section>

            <section class="featured-posts">
    <div class="post-grid">
        <div v-for="article in articles" 
             :key="article.id" 
             class="post-card"
             @click="goToArticle(article)">
            <div class="post-cover" :style="getRandomGradient()">
                <div class="post-category">{{ article.category }}</div>
            </div>
            <div class="post-content">
                <h3>{{ article.title }}</h3>
                <p class="post-excerpt">{{ article.content?.substring(0, 100) + '...' }}</p>
                <div class="post-meta">
                    <span class="post-date">
                        <i class="el-icon-date"></i>
                        {{ new Date(article.createTime).toLocaleDateString() }}
                    </span>
                    <span class="post-read-time">
                        <i class="el-icon-reading"></i>
                        {{ Math.ceil((article.content?.length || 0) / 400) }} 分钟阅读
                    </span>
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
import { ref, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'
import { getArticleList } from '@/api/article'
import { ElMessage } from 'element-plus'


const gradients = [
    'linear-gradient(135deg, #42b883 0%, #347474 100%)',
    'linear-gradient(135deg, #6366f1 0%, #a855f7 100%)',
    'linear-gradient(135deg, #3b82f6 0%, #06b6d4 100%)',
    'linear-gradient(135deg, #ec4899 0%, #f43f5e 100%)',
    'linear-gradient(135deg, #84cc16 0%, #22c55e 100%)',
    'linear-gradient(135deg, #eab308 0%, #f97316 100%)'
]

const getRandomGradient = () => {
    const randomIndex = Math.floor(Math.random() * gradients.length)
    return { background: gradients[randomIndex] }
}

const fetchArticles = async () => {
    try {
        const res = await getArticleList()
        if (res.code === 200) {
            // 处理返回的文章数据，添加默认图片
            articles.value = res.data.map(article => ({
                ...article,
                image: '/images/default.jpg', // 默认图片
                excerpt: article.content?.substring(0, 100) + '...',
                readTime: Math.ceil((article.content?.length || 0) / 400)
            }))
        }
    } catch (error) {
        console.error('获取文章列表失败:', error)
        ElMessage.error('获取文章列表失败')
    }
}
const router = useRouter()
const articles = ref([])

const goToLogin = () => {
    router.push('/login')
}
const goToArticle = (article) => {
    router.push({
        name: 'ArticleDetail',
        params: { id: article.id }
    })
}
const createNewPost = () => {
    router.push({
        name: 'Edit',
        params: { 
            id: 'new',
            isNew: true
        }
    })
}

const goToEdit = (article) => {
    router.push({
        name: 'Edit',
        params: {
            id: article.id,
            title: article.title,
            content: article.content,
            category: article.category,
            isEdit:true
        }
    })
}
let refreshInterval

onMounted(() => {
    fetchArticles() // 初始加载
    refreshInterval = setInterval(() => {
        fetchArticles()
    }, 60000) // 每60秒刷新一次
})

// 组件卸载时清除定时器
onUnmounted(() => {
    if (refreshInterval) {
        clearInterval(refreshInterval)
    }
})
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

.login-btn, .new-post-btn {
    background-color: #42b883;
    color: white;
    border: none;
    padding: 0.5rem 1rem;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.login-btn:hover, .new-post-btn:hover {
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
    cursor: pointer;
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
</style>