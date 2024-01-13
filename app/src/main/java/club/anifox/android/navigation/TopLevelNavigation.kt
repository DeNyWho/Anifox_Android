package club.anifox.android.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

fun NavGraphBuilder.topLevelScreen(
    navController: NavHostController,
) {
    composable(Screens.TopLevel.route) {
        val currentDestination = navController
            .currentBackStackEntryAsState().value?.destination

        Scaffold (
            bottomBar = {

            },
            contentWindowInsets = WindowInsets.navigationBars
        ) { padding ->
            NavHost(
                modifier = Modifier.zIndex(1f),
                navController = navController,
                startDestination = Screens.Home.route,
                contentAlignment = Alignment.Center,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() },
            ) {

            }
        }
    }
}