package club.anifox.android.domain.model.anime.light

import kotlinx.serialization.Serializable

@Serializable
data class AnimeLight(
    val title: String = "",
    val image: String = "",
    val url: String = "",
    val ratingMpa: String = "",
    val minimalAge: Int = 0,
)