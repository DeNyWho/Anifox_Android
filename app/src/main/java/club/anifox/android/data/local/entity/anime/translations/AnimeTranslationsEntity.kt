package club.anifox.android.data.local.entity.anime.translations

import androidx.room.Entity
import androidx.room.PrimaryKey
import club.anifox.android.domain.model.anime.translations.AnimeTranslations

@Entity(tableName = "anime_translations")
data class AnimeTranslationsEntity(
    @PrimaryKey val translationId: Int = 0,
    val title: String = "",
    val voice: String = "",
)

fun AnimeTranslationsEntity.asTranslation() = AnimeTranslations(
    id = translationId,
    title = title,
    voice = voice,
)