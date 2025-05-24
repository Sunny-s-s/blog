import request from '@/utils/request'

export function saveArticle(data) {
    return request({
        url: '/save',
        method: 'post',
        data
    })
}

export function getArticleList() {
    return request({
        url: '/list',
        method: 'get'
    })
}

export function getArticle(id) {
    return request({
        url: `/${id}`,
        method: 'get'
    })
}
