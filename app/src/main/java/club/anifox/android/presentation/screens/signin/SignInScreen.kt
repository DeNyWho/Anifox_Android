package club.anifox.android.presentation.screens.signin

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import club.anifox.android.presentation.common.ui.theme.AnifoxAndroidTheme
import org.koin.androidx.compose.getViewModel


@Composable
fun SignInScreen(
    viewModel: SignInViewModel = getViewModel(),
) {

}

@Composable
private fun SignInUI() {
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    AnifoxAndroidTheme(darkTheme = false) {
        SignInUI()
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    AnifoxAndroidTheme(darkTheme = true) {
        SignInUI()
    }
}