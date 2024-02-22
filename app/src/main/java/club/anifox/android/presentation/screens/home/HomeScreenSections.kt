package club.anifox.android.presentation.screens.home

sealed class HomeScreenSections(val name: String, val headerTitle: String) {
    data object AnimePopular: HomeScreenSections("popular", "Популярные")
    data object AnimeOngoing: HomeScreenSections("ongoing", "Аниме сезона")
}
