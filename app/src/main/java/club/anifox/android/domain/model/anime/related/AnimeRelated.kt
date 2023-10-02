package club.anifox.android.domain.model.anime.related

import club.anifox.android.domain.model.anime.light.AnimeLight
import kotlinx.serialization.Serializable

@Serializable
data class AnimeRelated(
    val anime: AnimeLight,
    val type: String,
)
