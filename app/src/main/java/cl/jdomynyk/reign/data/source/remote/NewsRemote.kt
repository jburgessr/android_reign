package cl.jdomynyk.reign.data.source.remote

import cl.jdomynyk.reign.data.entities.NewsEntity
import cl.jdomynyk.reign.domain.Handler

interface NewsRemote {
    fun getNews(handler: Handler<List<NewsEntity>>)
}