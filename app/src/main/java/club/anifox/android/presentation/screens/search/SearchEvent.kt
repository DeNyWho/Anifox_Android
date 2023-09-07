package club.anifox.android.presentation.screens.search

sealed class SearchEvent {
    data class OnSearchQueryChange(val query: String): SearchEvent()
    data class OnSortToggled(val sort: Boolean): SearchEvent()
    object OnSearchInitiated: SearchEvent()
    object ClearSearch: SearchEvent()
}