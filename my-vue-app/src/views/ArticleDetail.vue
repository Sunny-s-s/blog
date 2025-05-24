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
            <section class="article-container">
                <div class="article-header">
                    <h1>{{ article.title }}</h1>
                    <div class="article-meta">
                        <span><i class="el-icon-date"></i> {{ formatDate(article.createTime) }}</span>
                        <span><i class="el-icon-folder"></i> {{ article.category }}</span>
                        <span><i class="el-icon-reading"></i> {{ readTime }} 分钟阅读</span>
                    </div>
                </div>

                <div class="article-content">
                    {{ article.content }}
                </div>

                <div class="article-footer">
                    <button @click="goBack" class="back-btn">
                        <i class="el-icon-arrow-left"></i> 返回列表
                    </button>
                </div>
            </section>
        </main>

        <footer class="footer">
            <p>&copy; 2024 MyBlog. All rights reserved.</p>
        </footer>
    </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getArticle } from '@/api/article'
import { ElMessage } from 'element-plus'

const route = useRoute()
const router = useRouter()
const article = ref({})

// 计算阅读时间
const readTime = computed(() => {
    return Math.ceil((article.value.content?.length || 0) / 400)
})

const fetchArticle = async () => {
    try {
        const res = await getArticle(route.params.id)
        if (res.code === 200) {
            article.value = res.data
        } else {
            ElMessage.error(res.message || '获取文章失败')
            router.push('/articles')
        }
    } catch (error) {
        console.error('获取文章详情失败:', error)
        ElMessage.error('获取文章失败')
        router.push('/articles')
    }
}

const formatDate = (date) => {
    if (!date) return ''
    return new Date(date).toLocaleDateString('zh-CN', {
        year: 'numeric',
        month: 'long',
        day: 'numeric'
    })
}

const goBack = () => {
    router.back()
}

const goToLogin = () => {
    router.push('/login')
}

onMounted(() => {
    fetchArticle()
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

.article-container {
    max-width: 800px;
    margin: 0 auto;
    background: white;
    border-radius: 8px;
    box-shadow: 0 2px 12px rgba(0,0,0,0.1);
    padding: 2rem;
}

.article-header {
    margin-bottom: 2rem;
    padding-bottom: 1rem;
    border-bottom: 1px solid #eee;
}

.article-header h1 {
    font-size: 2.5rem;
    color: #2c3e50;
    margin-bottom: 1rem;
}

.article-meta {
    display: flex;
    gap: 2rem;
    color: #666;
    font-size: 0.9rem;
}

.article-meta span {
    display: flex;
    align-items: center;
    gap: 0.5rem;
}

.article-content {
    line-height: 1.8;
    color: #2c3e50;
    font-size: 1.1rem;
}

.article-footer {
    margin-top: 3rem;
    padding-top: 1rem;
    border-top: 1px solid #eee;
}

.back-btn {
    padding: 0.5rem 1rem;
    background: #f8f9fa;
    border: 1px solid #dee2e6;
    border-radius: 4px;
    color: #666;
    cursor: pointer;
    display: flex;
    align-items: center;
    gap: 0.5rem;
    transition: all 0.3s;
}

.back-btn:hover {
    background: #e9ecef;
    color: #2c3e50;
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

    .article-container {
        padding: 1rem;
    }

    .article-header h1 {
        font-size: 2rem;
    }

    .article-meta {
        flex-direction: column;
        gap: 0.5rem;
    }
}
</style>