package cl.jdomynyk.reign.data.source

import cl.jdomynyk.reign.data.entities.NewsEntity
import cl.jdomynyk.reign.data.entities.mapper.DataEntityMapper
import cl.jdomynyk.reign.data.source.local.news.NewsLocal
import cl.jdomynyk.reign.data.source.remote.NewsRemote
import cl.jdomynyk.reign.domain.Handler
import cl.jdomynyk.reign.domain.model.News
import cl.jdomynyk.reign.domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val dataEntityMapper: DataEntityMapper,
    private val newsRemote: NewsRemote,
    private val newsLocal: NewsLocal
) : NewsRepository {
    override fun getRemoteHits(handler: Handler<List<News>>) {
        newsRemote.getHits(object : Handler<List<NewsEntity>> {
            override fun success(result: List<NewsEntity>) {
                handler.success(dataEntityMapper.transformToDomain(result))
            }

            override fun error(exception: Exception) {
                handler.error(exception)
            }

        })
    }

    override suspend fun getLocalHits(): List<News> {
        return dataEntityMapper.transformToDomain(newsLocal.getAll())
    }

    override suspend fun saveHits(list: List<News>) {
        newsLocal.insertAllSync(dataEntityMapper.transformToEntity(list))
    }

    override suspend fun hideHit(id: Long) {
        newsLocal.hide(id)
    }

}