package club.anifox.android.domain.anime

import club.anifox.android.domain.anime.image.AnimeImage

data class AnimeLight(
    val title: String,
    val image: AnimeImage,
    val url: String,
    val rating: Double?,
    val minimalAge: Int,
)