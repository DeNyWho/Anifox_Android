package club.anifox.android.navigation

import android.view.Window
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import club.anifox.android.domain.enums.BottomNavTabs
import club.anifox.android.presentation.common.utils.OnDestinationChanged
import club.anifox.android.presentation.screens.detail.DetailScreen
import club.anifox.android.presentation.screens.search.SearchScreen
import club.anifox.android.presentation.screens.sign_in.SignInScreen
import club.anifox.android.presentation.screens.sign_up.SignUpScreen
import club.anifox.android.presentation.screens.splash.SplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun Navigation(window: Window) {

    val systemUiController = rememberSystemUiController()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Browse.route) {

        composable(Screens.Splash.route) {
            OnDestinationChanged(
                systemUiController = systemUiController,
                navigationBarColor = MaterialTheme.colorScheme.background,
                statusBarColor = MaterialTheme.colorScheme.background,
                drawOverStatusBar = true,
                window = window
            )

            SplashScreen(navController)
        }

        composable(Screens.SignIn.route) {
            OnDestinationChanged(
                systemUiController = systemUiController,
                navigationBarColor = MaterialTheme.colorScheme.background,
                statusBarColor = MaterialTheme.colorScheme.background,
                drawOverStatusBar = false,
                window = window
            )

            SignInScreen(navController = navController)
        }

        composable(Screens.SignUp.route) {
            OnDestinationChanged(
                systemUiController = systemUiController,
                navigationBarColor = MaterialTheme.colorScheme.background,
                statusBarColor = MaterialTheme.colorScheme.background,
                drawOverStatusBar = false,
                window = window
            )

            SignUpScreen(navController = navController)
        }

        composable(Screens.Search.route) {
            OnDestinationChanged(
                systemUiController = systemUiController,
                navigationBarColor = MaterialTheme.colorScheme.background,
                statusBarColor = MaterialTheme.colorScheme.background,
                drawOverStatusBar = false,
                window = window
            )

            SearchScreen(navController = navController)
        }

        composable(
            "${Screens.Detail.route}/{url}"
        ) { backStack ->
            OnDestinationChanged(
                systemUiController = systemUiController,
                navigationBarColor = Color.Transparent,
                statusBarColor = Color.Transparent,
                drawOverStatusBar = true,
                window = window
            )
            DetailScreen(
                url = backStack.arguments?.getString("url")!!,
                navigateBack = { navController.popBackStack() },
                onItemClick = { url ->
                    navController.navigate("${Screens.Detail.route}/$url")
                }
            )
        }

        composable(Screens.Browse.route) {
            OnDestinationChanged(
                systemUiController = systemUiController,
                navigationBarColor = MaterialTheme.colorScheme.background,
                statusBarColor = MaterialTheme.colorScheme.background,
                drawOverStatusBar = false,
                window = window
            )

            BottomNavigation(tabs = remember { mutableStateOf(BottomNavTabs.Browse) }, navController = navController)
        }

        composable(Screens.Home.route) {
            OnDestinationChanged(
                systemUiController = systemUiController,
                navigationBarColor = MaterialTheme.colorScheme.background,
                statusBarColor = MaterialTheme.colorScheme.background,
                drawOverStatusBar = false,
                window = window
            )

            BottomNavigation(tabs = remember { mutableStateOf(BottomNavTabs.Home) }, navController = navController)
        }

        composable(Screens.Favourite.route) {
            OnDestinationChanged(
                systemUiController = systemUiController,
                navigationBarColor = MaterialTheme.colorScheme.background,
                statusBarColor = MaterialTheme.colorScheme.background,
                drawOverStatusBar = false,
                window = window
            )

            BottomNavigation(tabs = remember { mutableStateOf(BottomNavTabs.Favourite) }, navController = navController)
        }

        composable(Screens.Profile.route) {
            OnDestinationChanged(
                systemUiController = systemUiController,
                navigationBarColor = MaterialTheme.colorScheme.background,
                statusBarColor = MaterialTheme.colorScheme.background,
                drawOverStatusBar = false,
                window = window
            )

            BottomNavigation(tabs = remember { mutableStateOf(BottomNavTabs.Profile) }, navController = navController)
        }


    }


}