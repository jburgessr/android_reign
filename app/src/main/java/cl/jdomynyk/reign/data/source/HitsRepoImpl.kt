package cl.jdomynyk.reign.data.source

import cl.jdomynyk.reign.data.entities.HitsEntity
import cl.jdomynyk.reign.data.entities.mapper.DataEntityMapper
import cl.jdomynyk.reign.data.source.local.HitsLocal
import cl.jdomynyk.reign.data.source.remote.HitsRemote
import cl.jdomynyk.reign.domain.Handler
import cl.jdomynyk.reign.domain.Hits
import cl.jdomynyk.reign.domain.HitsRepo

class HitsRepoImpl(
    private val dataEntityMapper: DataEntityMapper,
    private val hitsRemote: HitsRemote,
    private val hitsLocal: HitsLocal
) : HitsRepo {

    override fun getHits(handler: Handler<List<Hits>>) {
        hitsRemote.getHits(object : Handler<List<HitsEntity>> {
            override fun result(result: List<HitsEntity>) {
                handler.result(dataEntityMapper.transform(result))
            }

            override fun error(exception: Exception) {
                handler.error(exception)
            }

        })
    }

    override fun getHit(id: Long, handler: Handler<Hits>) {
        handler.result(dataEntityMapper.transform(hitsLocal.findByID(id))!!)
    }

}