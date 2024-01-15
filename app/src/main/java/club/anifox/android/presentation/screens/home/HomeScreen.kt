package club.anifox.android.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import club.anifox.android.presentation.common.ui.theme.AnifoxAndroidTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel(),
) {

}

@Composable
private fun HomeUI() {
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    AnifoxAndroidTheme(darkTheme = false) {
        HomeUI()
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    AnifoxAndroidTheme(darkTheme = true) {
        HomeUI()
    }
}
