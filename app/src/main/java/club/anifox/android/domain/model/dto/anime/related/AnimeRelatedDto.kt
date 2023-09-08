package club.anifox.android.domain.model.dto.anime.related

import club.anifox.android.data.local.mappers.toAnimeLight
import club.anifox.android.domain.model.anime.related.AnimeRelated
import club.anifox.android.domain.model.dto.anime.light.AnimeLightDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeRelatedDto(
    @SerialName("anime")
    val anime: AnimeLightDto,
    @SerialName("related")
    val related: RelatedInfoDto,
)

fun AnimeRelatedDto.toAnimeRelated(): AnimeRelated {
    return AnimeRelated(
        anime = anime.toAnimeLight(),
        type = related.type
    )
}