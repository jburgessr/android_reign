package cl.jdomynyk.reign.data.source

import cl.jdomynyk.reign.data.entities.mapper.DataEntityMapper
import cl.jdomynyk.reign.data.source.local.news.NewsLocal
import cl.jdomynyk.reign.data.source.remote.NewsService
import cl.jdomynyk.reign.domain.RemoteResult
import cl.jdomynyk.reign.domain.model.News
import cl.jdomynyk.reign.domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val dataEntityMapper: DataEntityMapper,
    private val newsService: NewsService,
    private val newsLocal: NewsLocal
) : NewsRepository {
    override suspend fun getRemoteNews(): RemoteResult<List<News>> {
        try {
            val result = newsService.getNews().await()
            if (result.isSuccessful) {
                return RemoteResult.Success(dataEntityMapper.transformToDomain(result.body()?.list))
            }
            return RemoteResult.Error(result.code(), result.message())
        } catch (exception: Exception) {
            return RemoteResult.Exception(exception)
        }
    }

    override suspend fun getLocalNews(): List<News> {
        return dataEntityMapper.transformToDomain(newsLocal.getAll())
    }

    override suspend fun saveNews(list: List<News>) {
        newsLocal.insertAllSync(dataEntityMapper.transformToEntity(list))
    }

    override suspend fun hideNews(id: Long) {
        newsLocal.hide(id)
    }

}