package club.anifox.android.navigation

sealed class NavArgs {
    data class DetailsNavArgs(
        val url: String
    ): NavArgs()
}