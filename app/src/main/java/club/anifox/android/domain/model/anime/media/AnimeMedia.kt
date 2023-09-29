package club.anifox.android.domain.model.anime.media

import kotlinx.serialization.Serializable


@Serializable
data class AnimeMedia(
    val playerUrl: String,
    val name: String,
    val kind: String,
    val hosting: String
)