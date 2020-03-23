package cl.jdomynyk.reign.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.jdomynyk.reign.data.entities.NewsEntity
import cl.jdomynyk.reign.data.source.local.news.NewsDao

@Database(
    entities = [NewsEntity::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao

//    companion object {
//        @Volatile
//        private var instance: AppDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: buildDatabase(context).also { instance = it }
//        }
//
//        private fun buildDatabase(context: Context) = Room.databaseBuilder(
//                context,
//                AppDatabase::class.java, "hits.db"
//            )
//            .build()
//    }
}