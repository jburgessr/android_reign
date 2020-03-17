package cl.jdomynyk.reign.data.source.local.news

import cl.jdomynyk.reign.data.entities.NewsEntity
import cl.jdomynyk.reign.data.source.local.news.NewsDao
import cl.jdomynyk.reign.data.source.local.news.NewsLocal

class NewsLocalImpl(private val newsDao: NewsDao) :
    NewsLocal {
    override suspend fun insertAllSync(list: List<NewsEntity>) = newsDao.insertAll(list)

    override suspend fun getAll(): List<NewsEntity> = newsDao.getAll()

    override suspend fun hide(id: Long) = newsDao.blocked(id)
}