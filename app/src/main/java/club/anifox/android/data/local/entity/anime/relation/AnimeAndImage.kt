package club.anifox.android.data.local.entity.anime.relation

import androidx.room.Embedded
import androidx.room.Relation
import club.anifox.android.data.local.entity.anime.AnimeEntity
import club.anifox.android.data.local.entity.anime.image.AnimeImageEntity

data class AnimeAndImage(
    @Embedded val anime: AnimeEntity,
    @Relation(
        parentColumn = "url",
        entityColumn = "animeUrl"
    )
    val image: AnimeImageEntity,
)