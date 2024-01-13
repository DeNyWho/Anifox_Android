package club.anifox.android.data.local.entity.anime.translations

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "anime_translations_cross_ref",
    primaryKeys = ["animeUrl", "translationId"],
    indices = [Index("translationId")],
)
data class AnimeTranslationsCrossRef(
    val animeUrl: String = "",
    val translationId: String = "",
)