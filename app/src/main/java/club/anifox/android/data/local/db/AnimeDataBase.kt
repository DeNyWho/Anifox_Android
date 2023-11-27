package club.anifox.android.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import club.anifox.android.data.local.entity.anime.AnimeEntity
import club.anifox.android.data.local.entity.anime.genres.AnimeGenresEntity
import club.anifox.android.data.local.entity.anime.image.AnimeImageEntity
import club.anifox.android.data.local.entity.anime.studio.AnimeStudioEntity
import club.anifox.android.data.local.entity.anime.translations.AnimeTranslationsEntity

@Database(entities = [AnimeEntity::class, AnimeGenresEntity::class, AnimeImageEntity::class, AnimeStudioEntity::class, AnimeTranslationsEntity::class], version = 1)
abstract class AnimeDatabase: RoomDatabase() {

}