package club.anifox.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import club.anifox.android.navigation.NavigationGraph
import club.anifox.android.presentation.common.ui.theme.AnifoxAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnifoxAndroidTheme {
                NavigationGraph(window = window)
            }
        }
    }
}
