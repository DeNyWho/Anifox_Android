package club.anifox.android.presentation.screens.browse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import club.anifox.android.R
import club.anifox.android.presentation.common.ui.theme.Anifox_AndroidTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun BrowseScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: BrowseViewModel = getViewModel()
) {
    Content(modifier)
}

@Composable
private fun Content(modifier: Modifier) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(R.string.in_plan_screen_browse),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    Anifox_AndroidTheme(useDarkTheme = false) {
        Content(Modifier)
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    Anifox_AndroidTheme(useDarkTheme = true) {
        Content(Modifier)
    }
}