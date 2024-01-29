package club.anifox.android.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import club.anifox.android.data.local.converter.LocalDateConverter
import club.anifox.android.data.local.converter.LocalDateTimeConverter
import club.anifox.android.data.local.dao.anime.AnimeDao
import club.anifox.android.data.local.dao.anime.GenreDao
import club.anifox.android.data.local.dao.anime.ImageDao
import club.anifox.android.data.local.dao.anime.StudioDao
import club.anifox.android.data.local.dao.anime.TranslationDao
import club.anifox.android.data.local.entity.anime.AnimeEntity
import club.anifox.android.data.local.entity.anime.genres.AnimeGenresEntity
import club.anifox.android.data.local.entity.anime.image.AnimeImageEntity
import club.anifox.android.data.local.entity.anime.studio.AnimeStudioEntity
import club.anifox.android.data.local.entity.anime.translations.AnimeTranslationsEntity

@Database(entities = [
    AnimeEntity::class,
    AnimeGenresEntity::class,
    AnimeImageEntity::class,
    AnimeStudioEntity::class,
    AnimeTranslationsEntity::class,
],
    version = 1,
    exportSchema = false,
)
@TypeConverters(LocalDateConverter::class, LocalDateTimeConverter::class)
abstract class AnifoxDatabase: RoomDatabase() {

    abstract fun animeDao(): AnimeDao

    abstract fun genreDao(): GenreDao

    abstract fun imageDao(): ImageDao

    abstract fun studioDao(): StudioDao

    abstract fun translationDao(): TranslationDao

    companion object {
        @Volatile
        private var instance: AnifoxDatabase? = null

        fun getInstance(context: Context): AnifoxDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(context.applicationContext, AnifoxDatabase::class.java, "anifox.db").build()
                }
            }
            return instance!!
        }
    }
}
