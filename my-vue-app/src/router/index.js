import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'

const routes = [
   {
    path: '/article/:id',
    name: 'ArticleDetail',
    component: () => import('@/views/ArticleDetail.vue'),
    meta: {
        requiresAuth: true
    }
  },
    {
        path:'/edit',
        name:'Edit',
        component: () => import('@/views/edit.vue'),
        meta: {
            requiresAuth: true // 需要登录才能访问
        }
    },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    meta: {
      requiresAuth: true // 需要登录才能访问
    }
  },
  {
    path: '/',      // 将根路径重定向到登录页
    redirect: '/login'  // 修改这里，重定向到登录页
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue')
  },
  {
    path: '/articles',
    name: 'Articles',
    component: () => import('@/views/Articles.vue'),
    meta: {
      requiresAuth: true
    }

  },
  {
    path: '/about',
    name: 'About',
    component: () => import('@/views/About.vue'),
    meta: {
      requiresAuth: true
    }

  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/Register.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 添加路由守卫
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!token) {
      next('/login')
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router