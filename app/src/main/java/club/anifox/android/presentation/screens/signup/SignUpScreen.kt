package club.anifox.android.presentation.screens.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import club.anifox.android.presentation.common.ui.theme.AnifoxAndroidTheme
import org.koin.androidx.compose.getViewModel


@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = getViewModel(),
) {

}

@Composable
private fun SignUpUI() {
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    AnifoxAndroidTheme(darkTheme = false) {
        SignUpUI()
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    AnifoxAndroidTheme(darkTheme = true) {
        SignUpUI()
    }
}