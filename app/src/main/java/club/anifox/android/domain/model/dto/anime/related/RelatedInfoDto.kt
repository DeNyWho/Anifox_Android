package club.anifox.android.domain.model.dto.anime.related

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RelatedInfoDto(
    @SerialName("type")
    val type: String,
    @SerialName("typeEn")
    val typeEn: String
)