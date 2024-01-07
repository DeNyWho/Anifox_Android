package club.anifox.android.domain.model.anime.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeStudio(
    @SerialName("id")
    val id: String,
    @SerialName("studio")
    val studio: String,
)
