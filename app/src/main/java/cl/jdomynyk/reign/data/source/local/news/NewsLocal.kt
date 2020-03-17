package cl.jdomynyk.reign.data.source.local.news

import cl.jdomynyk.reign.data.entities.NewsEntity

interface NewsLocal {
    suspend fun insertAllSync(list: List<NewsEntity>)
    suspend fun getAll(): List<NewsEntity>
    suspend fun hide(id: Long)
}