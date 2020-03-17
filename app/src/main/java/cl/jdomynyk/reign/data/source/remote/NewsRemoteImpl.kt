package cl.jdomynyk.reign.data.source.remote

import cl.jdomynyk.reign.data.entities.NewsEntity
import cl.jdomynyk.reign.data.exception.ServiceThrowable
import cl.jdomynyk.reign.data.exception.ServiceTimeOut
import cl.jdomynyk.reign.data.source.remote.response.NewsReponse
import cl.jdomynyk.reign.domain.Handler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class NewsRemoteImpl(private val newsService: NewsService) : NewsRemote {
    override fun getHits(handler: Handler<List<NewsEntity>>) {
        newsService.getMovies().enqueue(object : Callback<NewsReponse> {
            override fun onResponse(
                call: Call<NewsReponse>,
                response: Response<NewsReponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { handler.success(it.list) }
                } else {
                    handler.error(ServiceThrowable(response.code().toString()))
                }
            }

            override fun onFailure(call: Call<NewsReponse>, t: Throwable) {
                if (t is SocketTimeoutException)
                    handler.error(ServiceTimeOut())
                else
                    handler.error(ServiceThrowable(t.message.toString()))
            }
        })
    }

}