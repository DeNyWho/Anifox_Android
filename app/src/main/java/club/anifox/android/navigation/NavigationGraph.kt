package club.anifox.android.navigation

import android.view.Window
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import club.anifox.android.presentation.common.utils.OnDestinationChanged
import club.anifox.android.presentation.screens.detail.DetailScreen
import club.anifox.android.presentation.screens.home.HomeScreen
import club.anifox.android.presentation.screens.player.PlayerScreen
import club.anifox.android.presentation.screens.schedule.ScheduleScreen
import club.anifox.android.presentation.screens.search.SearchScreen
import club.anifox.android.presentation.screens.signin.SignInScreen
import club.anifox.android.presentation.screens.signup.SignUpScreen
import club.anifox.android.presentation.screens.splash.SplashScreen

@Composable
fun NavigationGraph(
    window: Window
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route,
        contentAlignment = Alignment.Center,
        enterTransition = { slideInHorizontally { it } },
        exitTransition = { slideOutHorizontally { -it / 4 } },
        popEnterTransition = { slideInHorizontally { -it / 4 } },
        popExitTransition = { slideOutHorizontally { it } }
    ) {
        composable(Screens.Splash.route) {
            OnDestinationChanged(
                navigationBarColor = MaterialTheme.colorScheme.background,
                statusBarColor = MaterialTheme.colorScheme.background,
                window = window,
            )

            SplashScreen(
                navigateToHome = { navController.navigate(Screens.Home.route) },
                navigateToSignIn = { navController.navigate(Screens.SignIn.route) }
            )
        }

        composable(Screens.Home.route) {
            OnDestinationChanged(
                navigationBarColor = MaterialTheme.colorScheme.background,
                statusBarColor = MaterialTheme.colorScheme.background,
                window = window,
            )

            HomeScreen()
        }

        composable(Screens.SignIn.route) {
            OnDestinationChanged(
                navigationBarColor = MaterialTheme.colorScheme.background,
                statusBarColor = MaterialTheme.colorScheme.background,
                window = window,
            )

            SignInScreen()
        }

        composable(Screens.SignUp.route) {
            OnDestinationChanged(
                navigationBarColor = MaterialTheme.colorScheme.background,
                statusBarColor = MaterialTheme.colorScheme.background,
                window = window,
            )

            SignUpScreen()
        }

        composable(Screens.Schedule.route) {
            OnDestinationChanged(
                navigationBarColor = MaterialTheme.colorScheme.background,
                statusBarColor = MaterialTheme.colorScheme.background,
                window = window,
            )

            ScheduleScreen()
        }

        composable(Screens.Search.route) {
            OnDestinationChanged(
                navigationBarColor = MaterialTheme.colorScheme.background,
                statusBarColor = MaterialTheme.colorScheme.background,
                window = window,
            )

            SearchScreen()
        }

        composable(Screens.Player.route) {
            OnDestinationChanged(
                navigationBarColor = MaterialTheme.colorScheme.background,
                statusBarColor = MaterialTheme.colorScheme.background,
                window = window,
            )

            PlayerScreen()
        }

        composable(Screens.Detail.route) {
            OnDestinationChanged(
                navigationBarColor = MaterialTheme.colorScheme.background,
                statusBarColor = MaterialTheme.colorScheme.background,
                window = window,
            )

            DetailScreen()
        }
    }

}
