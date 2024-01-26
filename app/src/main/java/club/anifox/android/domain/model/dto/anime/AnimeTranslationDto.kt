package club.anifox.android.domain.model.dto.anime

import kotlinx.serialization.Serializable

@Serializable
data class AnimeTranslationDto(
    val id: Int = 0,
    val title: String = "",
    val voice: String = "",
)
