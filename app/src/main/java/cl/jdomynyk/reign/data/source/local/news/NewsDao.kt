package cl.jdomynyk.reign.data.source.local.news

import androidx.room.*
import cl.jdomynyk.reign.data.entities.NewsEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM news WHERE blocked = 0 ORDER BY datetime(createAt) DESC")
    suspend fun getAll(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<NewsEntity>)

    @Query("UPDATE news SET blocked = 1 WHERE objectID = :id")
    suspend fun blocked(id: Long)
}