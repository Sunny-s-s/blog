<template>
    <div class="auth-container">
        <div class="auth-card">
            <div class="auth-header">
                <h2>注册账号</h2>
                <p>加入我们，开始分享你的故事</p>
            </div>
            <div class="auth-form">
                <div class="form-group">
                    <label for="username">用户名</label>
                    <input 
                        id="username"
                        v-model="username" 
                        type="text" 
                        placeholder="请设置用户名"
                    >
                </div>
                <div class="form-group">
                    <label for="email">邮箱</label>
                    <input 
                        id="email"
                        v-model="email" 
                        type="email" 
                        placeholder="请输入邮箱"
                    >
                </div>
                <div class="form-group">
                    <label for="password">密码</label>
                    <input 
                        id="password"
                        v-model="password" 
                        type="password" 
                        placeholder="请设置密码"
                    >
                </div>
                <div class="form-group">
                    <label for="confirmPassword">确认密码</label>
                    <input 
                        id="confirmPassword"
                        v-model="confirmPassword" 
                        type="password" 
                        placeholder="请再次输入密码"
                    >
                </div>
                <button @click="handleRegister" class="auth-button">注册</button>
                <div class="auth-links">
                    <router-link to="/login">已有账号？立即登录</router-link>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { register } from '@/api/user'
import { ElMessage } from 'element-plus'

const username = ref('')
const email = ref('')
const password = ref('')
const confirmPassword = ref('')
const router = useRouter()

const handleRegister = async () => {
    try {
        // 表单验证
        if (!username.value || !password.value) {
            ElMessage.warning('请填写完整信息')
            return
        }

        if (password.value !== confirmPassword.value) {
            ElMessage.warning('两次密码输入不一致')
            return
        }

        const res = await register({
            username: username.value,
            password: password.value
        })

        if (res.code === 200) {
            ElMessage.success('注册成功')
            router.push('/login')
        } else {
            ElMessage.error(res.message || '注册失败')
        }
    } catch (error) {
        console.error(error)
        ElMessage.error('服务器错误')
    }
}

// const register = () => {
//     // TODO: 实现注册逻辑
//     if (password.value !== confirmPassword.value) {
//         alert('两次输入的密码不一致')
//         return
//     }
//     console.log('注册:', username.value, email.value, password.value)
//     router.push('/login')
// }
</script>

<style scoped>
/* 复用登录页面的样式 */
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
    justify-content: center;
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