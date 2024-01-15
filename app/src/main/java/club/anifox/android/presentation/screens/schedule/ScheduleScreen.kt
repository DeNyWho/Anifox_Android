package club.anifox.android.presentation.screens.schedule

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import club.anifox.android.presentation.common.ui.theme.AnifoxAndroidTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun ScheduleScreen(
    viewModel: ScheduleViewModel = getViewModel(),
) {

}

@Composable
private fun ScheduleUI() {
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    AnifoxAndroidTheme(darkTheme = false) {
        ScheduleUI()
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    AnifoxAndroidTheme(darkTheme = true) {
        ScheduleUI()
    }
}
