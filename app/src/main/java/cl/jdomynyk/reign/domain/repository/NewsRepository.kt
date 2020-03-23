package cl.jdomynyk.reign.domain.repository

import cl.jdomynyk.reign.domain.Handler
import cl.jdomynyk.reign.domain.model.News

interface NewsRepository {
    fun getRemoteNews(handler: Handler<List<News>>)

    suspend fun getLocalNews(): List<News>

    suspend fun saveNews(list: List<News>)

    suspend fun hideNews(id: Long)
}