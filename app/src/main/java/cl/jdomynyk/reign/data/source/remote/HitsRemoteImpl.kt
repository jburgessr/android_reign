package cl.jdomynyk.reign.data.source.remote

import cl.jdomynyk.reign.data.entities.HitsEntity
import cl.jdomynyk.reign.data.exception.ServiceThrowable
import cl.jdomynyk.reign.data.exception.ServiceTimeOut
import cl.jdomynyk.reign.domain.Handler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class HitsRemoteImpl(private val hitsService: HitsService) : HitsRemote {

    override fun getHits(handler: Handler<List<HitsEntity>>) {
        hitsService.getMovies().enqueue(object : Callback<List<HitsEntity>> {
            override fun onResponse(
                call: Call<List<HitsEntity>>,
                response: Response<List<HitsEntity>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { handler.result(it) }
                } else {
                    handler.error(ServiceThrowable(response.code().toString()))
                }
            }

            override fun onFailure(call: Call<List<HitsEntity>>, t: Throwable) {
                if (t is SocketTimeoutException)
                    handler.error(ServiceTimeOut())
                else
                    handler.error(ServiceThrowable(t.message.toString()))
            }
        })
    }

}