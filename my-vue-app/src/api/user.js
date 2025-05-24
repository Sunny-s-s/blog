import request from '@/utils/request'

export function login(data) {
    return request({
        url: '/auth/login', // 修改为实际后端接口路径
        method: 'post',
        data
    })
}

export function register(data) {
    return request({
        url: '/auth/register', // 修改为实际后端接口路径
        method: 'post',
        data:{
            username: data.username,
            password: data.password,
            email: data.email
        }
    })
}

export function getUserInfo() {
    return request({
        url: '/users/info', // 修改为实际后端接口路径
        method: 'get'
    })
}