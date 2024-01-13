package club.anifox.android.data.local.model

sealed class AnimeKey {
    data class FetchAnime(val animeUrl: String): AnimeKey()
}