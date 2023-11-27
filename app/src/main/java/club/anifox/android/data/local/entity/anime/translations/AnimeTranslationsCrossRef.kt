package club.anifox.android.data.local.entity.anime.translations

import androidx.room.Entity

@Entity(tableName = "anime_translations_cross_ref", primaryKeys = ["animeUrl", "translationId"])
data class AnimeTranslationsCrossRef(
    val animeUrl: String,
    val translationId: String
)