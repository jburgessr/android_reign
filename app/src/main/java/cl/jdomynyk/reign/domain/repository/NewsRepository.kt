package cl.jdomynyk.reign.domain.repository

import cl.jdomynyk.reign.domain.RemoteResult
import cl.jdomynyk.reign.domain.model.News

interface NewsRepository {
    suspend fun getRemoteNews(): RemoteResult<List<News>>

    suspend fun getLocalNews(): List<News>

    suspend fun saveNews(list: List<News>)

    suspend fun hideNews(id: Long)
}