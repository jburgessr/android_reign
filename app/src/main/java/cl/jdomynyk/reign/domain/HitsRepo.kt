package cl.jdomynyk.reign.domain

interface HitsRepo {
    fun getHits(handler: Handler<List<Hits>>)
    fun getHit(id: Long, handler: Handler<Hits>)
}