package club.anifox.android.data.local.entity.anime.translations

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_translations")
data class AnimeTranslationsEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val voice: String
)
