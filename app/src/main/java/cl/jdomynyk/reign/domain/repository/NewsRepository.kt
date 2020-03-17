package cl.jdomynyk.reign.domain.repository

import cl.jdomynyk.reign.domain.Handler
import cl.jdomynyk.reign.domain.model.News

interface NewsRepository {
    fun getRemoteHits(handler: Handler<List<News>>)

    suspend fun getLocalHits(): List<News>

    suspend fun saveHits(list: List<News>)

    suspend fun hideHit(id: Long)
}