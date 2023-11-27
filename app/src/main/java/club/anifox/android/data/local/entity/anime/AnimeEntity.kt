package club.anifox.android.data.local.entity.anime

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import club.anifox.android.data.local.entity.anime.genres.AnimeGenresCrossRef
import club.anifox.android.data.local.entity.anime.image.AnimeImageEntity
import club.anifox.android.data.local.entity.anime.genres.AnimeGenresEntity
import club.anifox.android.data.local.entity.anime.studio.AnimeStudioCrossRef
import club.anifox.android.data.local.entity.anime.studio.AnimeStudioEntity
import club.anifox.android.data.local.entity.anime.translations.AnimeTranslationsCrossRef
import club.anifox.android.data.local.entity.anime.translations.AnimeTranslationsEntity
import club.anifox.android.domain.anime.AnimeSeason
import club.anifox.android.domain.anime.AnimeStatus
import club.anifox.android.domain.anime.AnimeType

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey val url: String,
    val title: String,
    @Embedded
    val image: AnimeImageEntity,
    val type: AnimeType,
    val rating: Double?,
    val minimalAge: Int,
    val year: Int,
    val status: AnimeStatus,
    val season: AnimeSeason,
    val episodesCount: Int,
    val episodesAired: Int,
    @Relation(
        parentColumn = "url",
        entityColumn = "animeUrl",
        associateBy = Junction(AnimeGenresCrossRef::class)
    )
    val genres: List<AnimeGenresEntity>,
    @Relation(
        parentColumn = "url",
        entityColumn = "animeUrl",
        associateBy = Junction(AnimeStudioCrossRef::class)
    )
    val studios: List<AnimeStudioEntity>,
    val nextEpisode: String,
    val releasedOn: String,
    val airedOn: String,
    val description: String,
    @Relation(
        parentColumn = "url",
        entityColumn = "animeUrl",
        associateBy = Junction(AnimeTranslationsCrossRef::class)
    )
    val translations: List<AnimeTranslationsEntity>,
)