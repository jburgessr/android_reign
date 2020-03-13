package cl.jdomynyk.reign.data.entities.mapper

import cl.jdomynyk.reign.data.entities.HitsEntity
import cl.jdomynyk.reign.domain.Hits
import javax.inject.Inject

class DataEntityMapper @Inject constructor() {

    fun transform(hitsEntity: HitsEntity?): Hits? {
        var entity: Hits? = null
        if (hitsEntity != null) {
            entity = Hits(
                hitsEntity.storyID,
                hitsEntity.storyText,
                hitsEntity.storyTitle,
                hitsEntity.title,
                hitsEntity.author,
                hitsEntity.create
            )
        }
        return entity
    }

    fun transform(hitsLis: List<HitsEntity>): List<Hits> {
        val list = mutableListOf<Hits>()
        for (item in hitsLis) {
            val entity = transform(item)
            if (entity != null) {
                list.add(entity)
            }
        }
        return list
    }

}