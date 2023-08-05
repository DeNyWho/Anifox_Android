package club.anifox.android.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import club.anifox.android.navigation.Navigation
import club.anifox.android.presentation.common.ui.theme.Anifox_AndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Anifox_AndroidTheme {
                Navigation(window = window)
            }
        }
    }
}