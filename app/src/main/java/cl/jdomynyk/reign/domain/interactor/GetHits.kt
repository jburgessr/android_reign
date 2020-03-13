package cl.jdomynyk.reign.domain.interactor

import cl.jdomynyk.reign.domain.Handler
import cl.jdomynyk.reign.domain.Hits
import cl.jdomynyk.reign.domain.HitsRepo

class GetHits(private val repository: HitsRepo) : UseCase<List<Hits>> {

    override fun execute(handler: Handler<List<Hits>>) {
        repository.getHits(object : Handler<List<Hits>> {
            override fun result(result: List<Hits>) {
                handler.result(result)
            }

            override fun error(exception: Exception) {
                handler.error(exception)
            }
        })

    }
}