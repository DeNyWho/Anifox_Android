package club.anifox.android.presentation.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import club.anifox.android.presentation.common.ui.theme.AnifoxAndroidTheme
import org.koin.androidx.compose.getViewModel


@Composable
fun DetailScreen(
    viewModel: DetailViewModel = getViewModel(),
) {

}

@Composable
private fun DetailUI() {
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    AnifoxAndroidTheme(darkTheme = false) {
        DetailUI()
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    AnifoxAndroidTheme(darkTheme = true) {
        DetailUI()
    }
}
