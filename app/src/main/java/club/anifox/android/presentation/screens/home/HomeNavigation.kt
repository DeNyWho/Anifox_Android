package club.anifox.android.presentation.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import club.anifox.android.navigation.Screens

fun NavGraphBuilder.homeScreen(
    padding: PaddingValues,
    onAnimeClick: (String) -> Unit,
) {
    composable(
        route = Screens.Home.route
    ) {

    }
}