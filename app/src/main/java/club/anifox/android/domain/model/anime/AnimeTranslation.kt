package club.anifox.android.domain.model.anime

import kotlinx.serialization.Serializable

@Serializable
data class AnimeTranslation(
    val id: Int = 0,
    val title: String = "",
    val voice: String = "",
)