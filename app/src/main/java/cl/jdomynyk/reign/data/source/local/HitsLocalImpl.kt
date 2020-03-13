package cl.jdomynyk.reign.data.source.local

import cl.jdomynyk.reign.data.entities.HitsEntity

class HitsLocalImpl(private val hitsDao: HitsDao) : HitsLocal {
    override fun insertAll(list: List<HitsEntity>) {
        hitsDao.insertAll(list)
    }

    override fun findByID(id: Long): HitsEntity {
        return hitsDao.findByID(id)
    }

    override fun getAll(): List<HitsEntity> {
        return hitsDao.getAll()
    }
}