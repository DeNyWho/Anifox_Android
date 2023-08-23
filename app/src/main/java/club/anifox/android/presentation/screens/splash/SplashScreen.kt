package club.anifox.android.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import club.anifox.android.R
import club.anifox.android.navigation.Screens
import club.anifox.android.presentation.common.ui.theme.Anifox_AndroidTheme

@Composable
fun SplashScreen(navController: NavHostController) {
    SplashUI()
    navController.navigate(Screens.SignIn.route)
}



@Composable
fun SplashUI(){
    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.background).fillMaxSize().height(
            (LocalConfiguration.current.screenHeightDp * 0.2f).dp
        ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.anifox_logo___text ),
            contentDescription = "stringResource(R.string.logo)"
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    Anifox_AndroidTheme(useDarkTheme = false) {
        SplashUI()
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    Anifox_AndroidTheme(useDarkTheme = true) {
        SplashUI()
    }
}