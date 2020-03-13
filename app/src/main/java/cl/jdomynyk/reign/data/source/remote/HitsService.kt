package cl.jdomynyk.reign.data.source.remote

import cl.jdomynyk.reign.data.entities.HitsEntity
import retrofit2.Call
import retrofit2.http.GET

interface HitsService {
    @GET("search_by_date?query=android")
    fun getMovies(): Call<List<HitsEntity>>
}