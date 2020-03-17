package cl.jdomynyk.reign.data.entities.mapper

import cl.jdomynyk.reign.data.entities.NewsEntity
import cl.jdomynyk.reign.domain.model.News
import javax.inject.Inject

class DataEntityMapper @Inject constructor() {

    private fun transformToDomain(newsEntity: NewsEntity): News {
        return News(
            newsEntity.objectID,
            newsEntity.storyTitle,
            newsEntity.storyText,
            newsEntity.title,
            newsEntity.author,
            newsEntity.createAt,
            newsEntity.storyURL,
            newsEntity.url
        )
    }

    private fun transformToEntity(news: News): NewsEntity {
        return NewsEntity(
            news.objectID,
            news.storyTitle,
            news.storyText,
            news.title,
            news.author,
            news.createAt,
            news.storyURL,
            news.url
        )
    }

    fun transformToDomain(newsList: List<NewsEntity>): List<News> {
        val list = mutableListOf<News>()
        for (item in newsList) {
            val entity = transformToDomain(item)
            list.add(entity)
        }
        return list
    }

    fun transformToEntity(newsList: List<News>): List<NewsEntity> {
        val list = mutableListOf<NewsEntity>()
        for (item in newsList) {
            val entity = transformToEntity(item)
            list.add(entity)
        }
        return list
    }

}