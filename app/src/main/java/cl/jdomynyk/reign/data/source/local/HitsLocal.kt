package cl.jdomynyk.reign.data.source.local

import cl.jdomynyk.reign.data.entities.HitsEntity

interface HitsLocal {
    fun insertAll(list: List<HitsEntity>)
    fun findByID(id: Long): HitsEntity
    fun getAll(): List<HitsEntity>
}