package cl.jdomynyk.reign.data.source.remote

import cl.jdomynyk.reign.data.source.remote.response.NewsReponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {
    @GET("v1/search_by_date?query=android")
    fun getNews(): Deferred<Response<NewsReponse>>
}