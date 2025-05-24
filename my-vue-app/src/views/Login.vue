<template>
    <div class="auth-container">
        <div class="auth-card">
            <div class="auth-header">
                <h2>登录</h2>
                <p>欢迎回来！</p>
            </div>
            <div class="auth-form">
                <div class="form-group">
                    <label for="username">用户名</label>
                    <input 
                        id="username"
                        v-model="username" 
                        type="text" 
                        placeholder="请输入用户名"
                    >
                </div>
                <div class="form-group">
                    <label for="password">密码</label>
                    <input 
                        id="password"
                        v-model="password" 
                        type="password" 
                        placeholder="请输入密码"
                    >
                </div>
                <button @click="handleLogin" class="auth-button">登录</button>
                <div class="auth-links">
                    <a href="#" @click.prevent="forgotPassword">忘记密码？</a>
                    <router-link to="/register">没有账号？立即注册</router-link>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { login } from '@/api/user'
import { ElMessage } from 'element-plus'
const username = ref('')
const password = ref('')
const router = useRouter()

const handleLogin = async () => {
    try {
        // 表单验证
        if (!username.value || !password.value) {
            ElMessage.warning('请输入用户名和密码')
            return
        }

        const res = await login({
            username: username.value,
            password: password.value
        })

        if (res.code === 200) {
            // 保存token
            localStorage.setItem('token', res.data.token)
            ElMessage.success('登录成功')
            router.push('/home')
        } else {
            ElMessage.error(res.message || '登录失败')
        }
    } catch (error) {
        console.error(error)
        ElMessage.error('服务器错误')
    }
}
// const login = () => {
//     // TODO: 实现登录逻辑
//     console.log('登录:', username.value, password.value)
//     router.push('/home')
// }

const forgotPassword = () => {
    // TODO: 实现忘记密码逻辑
    console.log('忘记密码')
}
</script>

<style scoped>
.auth-container {
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background: linear-gradient(135deg, #42b883 0%, #347474 100%);
    padding: 1rem;
}

.auth-card {
    background: white;
    border-radius: 8px;
    padding: 2rem;
    width: 100%;
    max-width: 400px;
    box-shadow: 0 4px 16px rgba(0,0,0,0.1);
}

.auth-header {
    text-align: center;
    margin-bottom: 2rem;
}

.auth-header h2 {
    color: #2c3e50;
    font-size: 1.8rem;
    margin-bottom: 0.5rem;
}

.auth-header p {
    color: #666;
}

.form-group {
    margin-bottom: 1.5rem;
}

.form-group label {
    display: block;
    margin-bottom: 0.5rem;
    color: #2c3e50;
    font-weight: 500;
}

.form-group input {
    width: 100%;
    padding: 0.75rem;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 1rem;
    transition: border-color 0.3s;
}

.form-group input:focus {
    outline: none;
    border-color: #42b883;
}

.auth-button {
    width: 100%;
    padding: 0.75rem;
    background-color: #42b883;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s;
}

.auth-button:hover {
    background-color: #3aa876;
}

.auth-links {
    margin-top: 1.5rem;
    display: flex;
    justify-content: space-between;
    font-size: 0.9rem;
}

.auth-links a {
    color: #42b883;
    text-decoration: none;
    transition: color 0.3s;
}

.auth-links a:hover {
    color: #3aa876;
}
</style>