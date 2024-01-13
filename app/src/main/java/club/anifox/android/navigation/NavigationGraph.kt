package club.anifox.android.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val topLevelNavController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route,
        modifier = modifier,
        contentAlignment = Alignment.Center,
        enterTransition = { slideInHorizontally { it } },
        exitTransition = { slideOutHorizontally { -it / 4 } },
        popEnterTransition = { slideInHorizontally { -it / 4 } },
        popExitTransition = { slideOutHorizontally { it } }
    ) {

    }
}