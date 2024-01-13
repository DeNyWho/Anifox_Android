package club.anifox.android.presentation.screens.splash

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import club.anifox.android.navigation.Screens

fun NavGraphBuilder.splashScreen(
    padding: PaddingValues,
    navigateToSignIn: () -> Unit,
    navigateToHome: () -> Unit,
) {
    composable(
        route = Screens.Splash.route
    ) {
        SplashScreen(navigateToHome, navigateToSignIn)
    }
}