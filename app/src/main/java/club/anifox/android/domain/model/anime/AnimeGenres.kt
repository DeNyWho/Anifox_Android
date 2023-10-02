package club.anifox.android.domain.model.anime

import kotlinx.serialization.Serializable

@Serializable
data class AnimeGenres(
    val id: String,
    val genre: String,
)
