package cl.jdomynyk.reign.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.jdomynyk.reign.data.entities.HitsEntity

@Database(
    entities = [HitsEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getHitsDao(): HitsDao

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