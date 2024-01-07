package club.anifox.android.domain.model.anime.common

import kotlinx.serialization.Serializable

@Serializable
data class AnimeGenres(
    val id: String,
    val name: String,
)
