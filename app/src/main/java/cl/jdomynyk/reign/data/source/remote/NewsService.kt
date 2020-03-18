package cl.jdomynyk.reign.data.source.remote

import cl.jdomynyk.reign.data.source.remote.response.NewsReponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsService {
    @GET("v1/search_by_date?query=android")
    fun getNews(): Call<NewsReponse>
}