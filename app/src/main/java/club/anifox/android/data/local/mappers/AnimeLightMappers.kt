package club.anifox.android.data.local.mappers

import club.anifox.android.data.local.cache.entity.content.AnimeLightEntity
import club.anifox.android.domain.model.anime.AnimeLight
import club.anifox.android.domain.model.dto.anime.AnimeLightDto
import java.util.UUID


fun AnimeLightDto.toAnimeLightEntity(): AnimeLightEntity {
    return AnimeLightEntity(
        pk = UUID.randomUUID().toString(),
        url = url,
        image = image.medium,
        title = title
    )
}

fun AnimeLight.toAnimeLightEntity(): AnimeLightEntity {
    return AnimeLightEntity(
        pk = UUID.randomUUID().toString(),
        url = url,
        image = image,
        title = title
    )
}


fun AnimeLightDto.toAnimeLight(): AnimeLight {
    return AnimeLight(
        url = url,
        title = title,
        image = image.medium
    )
}

fun AnimeLightEntity.toAnimeLight(): AnimeLight {
    return AnimeLight(
        url = url,
        title = title,
        image = image
    )
}