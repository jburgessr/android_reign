package cl.jdomynyk.reign.domain.usecase

import cl.jdomynyk.reign.domain.model.News
import cl.jdomynyk.reign.domain.repository.NewsRepository
import cl.jdomynyk.reign.domain.Handler

class NewsUseCase(private val repository: NewsRepository) {

    fun findRemote(handler: Handler<List<News>>) {
        repository.getRemoteHits(object : Handler<List<News>> {
            override fun success(result: List<News>) {
                handler.success(result)
            }

            override fun error(exception: Exception) {
                handler.error(exception)
            }

        })
    }

    suspend fun getAll(): List<News> {
        return repository.getLocalHits()
    }

    suspend fun blockedNews(id: Long) {
        repository.hideHit(id)
    }

    suspend fun saveAllNews(list: List<News>) {
        repository.saveHits(list)
    }
}