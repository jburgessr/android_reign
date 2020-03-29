package cl.jdomynyk.reign.domain.usecase

import cl.jdomynyk.reign.domain.RemoteResult
import cl.jdomynyk.reign.domain.model.News
import cl.jdomynyk.reign.domain.repository.NewsRepository

class NewsUseCase(private val repository: NewsRepository) {

    suspend  fun findRemote(): RemoteResult<List<News>> {
        return repository.getRemoteNews()
    }

    suspend fun getAll(): List<News> {
        return repository.getLocalNews()
    }

    suspend fun blockedNews(id: Long) {
        repository.hideNews(id)
    }

    suspend fun saveAllNews(list: List<News>) {
        repository.saveNews(list)
    }
}