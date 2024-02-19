package club.anifox.android.presentation.screens.home

sealed class HomeScreenSections(val name: String, val headerTitle: String) {
    data object AnimePopular: HomeScreenSections("popular", "popular")
    data object AnimeOngoing: HomeScreenSections("ongoing", "ongoing")
}
