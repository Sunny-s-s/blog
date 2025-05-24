import axios from 'axios'
import { ElMessage } from 'element-plus'
import router from '@/router'

 // 后端服务地址
const service = axios.create({
    baseURL: 'http://localhost:8080/api/v1', // 基础URL
    timeout: 5000,
    withCredentials: true // 允许携带cookie
})

// 请求拦截器
service.interceptors.request.use(
    config => {
        const token = localStorage.getItem('token')
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`
        }
        return config
    },
    error => {
        return Promise.reject(error)
    }
)

// 响应拦截器
service.interceptors.response.use(
    response => {
        const res = response.data
        // 处理自定义错误码
        if (res.code !== 200) {
            ElMessage.error(res.message || '请求失败')
            if (res.code === 401) {
                // token过期或未登录
                localStorage.removeItem('token')
                router.push('/login')
            }
            return Promise.reject(res)
        }
        return res
    },
    error => {
        ElMessage.error(error.message || '网络错误')
        return Promise.reject(error)
    }
)

export default service