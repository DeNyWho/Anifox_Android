package club.anifox.android.presentation.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    background = light_background,
    onBackground = light_onBackground,
)

private val LightColorScheme = lightColorScheme(
    background = dark_background,
    onBackground = dark_onBackground,
)

@Composable
fun AnifoxAndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (!darkTheme) {
        LightColorScheme
    } else {
        DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        content = content,
        typography = Typography,
    )
}
