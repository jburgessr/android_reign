package cl.jdomynyk.reign.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.jdomynyk.reign.data.entities.HitsEntity

@Dao
interface HitsDao {
    @Query("SELECT * FROM hits")
    fun getAll(): List<HitsEntity>

    @Query("SELECT * FROM hits WHERE storyID LIKE :id")
    fun findByID(id: Long): HitsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<HitsEntity>)
}