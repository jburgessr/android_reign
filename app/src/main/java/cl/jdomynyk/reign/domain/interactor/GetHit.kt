package cl.jdomynyk.reign.domain.interactor

import cl.jdomynyk.reign.domain.Handler
import cl.jdomynyk.reign.domain.Hits
import cl.jdomynyk.reign.domain.HitsRepo

class GetHit(private val repo: HitsRepo) : UseCase<Hits, GetHit.Params> {
    override fun execute(handler: Handler<Hits>, params: Params) {
        repo.getHit(params.id, object : Handler<Hits> {
            override fun result(result: Hits) {
                handler.result(result)
            }

            override fun error(exception: Exception) {
                handler.error(exception)
            }

        })
    }

    class Params(val id: Long)
}