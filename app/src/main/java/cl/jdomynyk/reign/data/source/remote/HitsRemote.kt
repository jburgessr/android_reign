package cl.jdomynyk.reign.data.source.remote

import cl.jdomynyk.reign.data.entities.HitsEntity
import cl.jdomynyk.reign.domain.Handler

interface HitsRemote {
    fun getHits(handler: Handler<List<HitsEntity>>)
}