package club.anifox.android.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import club.anifox.android.data.local.cache.dao.content.AnimeLightDao
import club.anifox.android.data.local.cache.dao.content.AnimeLightRemoteKeyDao
import club.anifox.android.data.local.cache.dao.content.SearchHistoryDao
import club.anifox.android.data.local.cache.entity.content.AnimeLightEntity
import club.anifox.android.data.local.cache.entity.content.AnimeLightRemoteKeyEntity
import club.anifox.android.data.local.cache.entity.content.SearchHistoryEntity

@Database(entities = [AnimeLightEntity::class, AnimeLightRemoteKeyEntity::class, SearchHistoryEntity::class], version = 1)
abstract class AniFoxDataBase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao
    abstract fun animeLightDao(): AnimeLightDao
    abstract fun animeLightRemoteKeyDao(): AnimeLightRemoteKeyDao

    companion object {
        @Volatile
        private var instance: AniFoxDataBase? = null

        fun getInstance(context: Context): AniFoxDataBase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(context.applicationContext, AniFoxDataBase::class.java, "anifox.db").build()
                }
            }
            return instance!!
        }
    }
}
