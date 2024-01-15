package club.anifox.android.presentation.screens.player

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import club.anifox.android.presentation.common.ui.theme.AnifoxAndroidTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun PlayerScreen(
    viewModel: PlayerViewModel = getViewModel(),
) {

}

@Composable
private fun PlayerUI() {
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    AnifoxAndroidTheme(darkTheme = false) {
        PlayerUI()
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    AnifoxAndroidTheme(darkTheme = true) {
        PlayerUI()
    }
}
