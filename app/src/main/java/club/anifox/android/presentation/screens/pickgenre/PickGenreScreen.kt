package club.anifox.android.presentation.screens.pickgenre

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import club.anifox.android.R
import club.anifox.android.presentation.common.ui.theme.Anifox_AndroidTheme

@Composable
fun PickGenreScreen(
    navController: NavHostController,
) {
    Content(modifier = Modifier)
}

@Composable
private fun Content(modifier: Modifier) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
//        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.End),
            text = stringResource(R.string.skip_btn),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(R.string.pick_genre_main),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = stringResource(R.string.pick_genre_info),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
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
