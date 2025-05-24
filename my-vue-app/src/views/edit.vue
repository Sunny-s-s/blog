<template>
    <div class="edit-container">
        <div class="edit-header">
            <div class="header-left">
                <button @click="goBack" class="back-btn">
                    <i class="el-icon-arrow-left"></i> 返回
                </button>
                <h2>{{ isEdit ? '编辑文章' : '写文章' }}</h2>
            </div>
            <div class="header-right">
                <button @click="saveDraft" class="draft-btn">保存草稿</button>
                <button @click="publishPost" class="publish-btn">发布文章</button>
            </div>
        </div>

        <div class="edit-main">
            <div class="edit-form">
                <input 
                    v-model="title" 
                    type="text" 
                    class="title-input" 
                    placeholder="请输入文章标题..."
                >
                
                <div class="category-select">
                    <el-select v-model="category" placeholder="选择分类">
                        <el-option label="技术" value="tech"/>
                        <el-option label="生活" value="life"/>
                        <el-option label="其他" value="other"/>
                    </el-select>
                </div>

                <el-input
                    v-model="content"
                    type="textarea"
                    class="content-editor"
                    :rows="20"
                    placeholder="请输入文章内容..."
                />
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter,useRoute } from 'vue-router'
import { ElMessage } from 'element-plus'
import { onMounted } from 'vue'
import { saveArticle } from '@/api/article'
const route = useRoute()
const router = useRouter()
const title = ref('')
const content = ref('')
const category = ref('')
const isEdit = ref(false)

// 添加获取用户ID的方法
const getUserId = () => {
    const user = JSON.parse(localStorage.getItem('user'))
    return user ? user.id : null
}



const goBack = () => {
    router.back()
}

const saveDraft = async () => {
    if (!title.value || !content.value) {
        ElMessage.warning('请填写文章标题和内容')
        return
    }

    try {
        const res = await saveArticle({
            title: title.value,
            content: content.value,
            category: category.value,
            status: 0, // 草稿状态
            authorId: getUserId() // 需要实现获取当前用户ID的方法
        })

        if (res.code === 200) {
            ElMessage.success('草稿保存成功')
        } else {
            ElMessage.error(res.message || '保存失败')
        }
    } catch (error) {
        console.error(error)
        ElMessage.error('服务器错误')
    }
}


onMounted(() => {
    // 根据路由参数判断是新建还是编辑
    if (route.params.id === 'new') {
        isEdit.value = false
        // 清空表单
        title.value = ''
        content.value = ''
        category.value = ''
    } else {
        isEdit.value = true
        // 加载现有文章数据
        if (route.params.title) {
            title.value = route.params.title
        }
        if (route.params.content) {
            content.value = route.params.content
        }
    }
})
const publishPost = async () => {
    if (!title.value || !content.value || !category.value) {
        ElMessage.warning('请填写完整文章信息')
        return
    }

    try {
        const res = await saveArticle({
            title: title.value,
            content: content.value,
            category: category.value,
            status: 1, // 发布状态
            authorId: getUserId() // 需要实现获取当前用户ID的方法
        })

        if (res.code === 200) {
            ElMessage.success('文章发布成功')
            router.push('/home')
        } else {
            ElMessage.error(res.message || '发布失败')
        }
    } catch (error) {
        console.error(error)
        ElMessage.error('服务器错误')
    }
}

</script>

<style scoped>
.edit-container {
    min-height: 100vh;
    background: #f5f7fa;
}

.edit-header {
    background: white;
    padding: 1rem 2rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.header-left {
    display: flex;
    align-items: center;
    gap: 1rem;
}

.header-left h2 {
    margin: 0;
    font-size: 1.25rem;
    color: #2c3e50;
}

.back-btn {
    border: none;
    background: none;
    color: #666;
    cursor: pointer;
    display: flex;
    align-items: center;
    gap: 0.5rem;
    padding: 0.5rem;
    border-radius: 4px;
    transition: background-color 0.3s;
}

.back-btn:hover {
    background-color: #f5f7fa;
}

.header-right {
    display: flex;
    gap: 1rem;
}

.draft-btn {
    padding: 0.5rem 1rem;
    border: 1px solid #dcdfe6;
    background: white;
    color: #606266;
    border-radius: 4px;
    cursor: pointer;
    transition: all 0.3s;
}

.draft-btn:hover {
    border-color: #c6e2ff;
    color: #409eff;
    background-color: #ecf5ff;
}

.publish-btn {
    padding: 0.5rem 1rem;
    border: none;
    background: #42b883;
    color: white;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.publish-btn:hover {
    background-color: #3aa876;
}

.edit-main {
    padding: 2rem;
    max-width: 1200px;
    margin: 0 auto;
}

.edit-form {
    background: white;
    padding: 2rem;
    border-radius: 8px;
    box-shadow: 0 2px 12px rgba(0,0,0,0.1);
}

.title-input {
    width: 100%;
    padding: 1rem;
    font-size: 1.5rem;
    border: none;
    border-bottom: 1px solid #dcdfe6;
    margin-bottom: 1rem;
    outline: none;
}

.title-input:focus {
    border-bottom-color: #42b883;
}

.category-select {
    margin-bottom: 1rem;
}

.content-editor {
    width: 100%;
    font-size: 1rem;
    line-height: 1.6;
}

:deep(.el-textarea__inner) {
    font-family: inherit;
}
</style>