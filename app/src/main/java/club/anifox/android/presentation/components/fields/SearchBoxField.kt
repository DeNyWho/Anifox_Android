package club.anifox.android.presentation.components.fields

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import club.anifox.android.R
import club.anifox.android.presentation.common.ui.icons.MyIcons
import club.anifox.android.presentation.common.ui.theme.Anifox_AndroidTheme

@Composable
fun SearchBoxField(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = false,
    searchQuery: String = "",
    placeHolder: String = "",
    focusRequest: FocusRequester = FocusRequester(),
    onSearchQueryChanged: (String) -> Unit = { },
    onSearchQueryCleared: () -> Unit = { },
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground,
                shape = MaterialTheme.shapes.small,
            )
            .focusRequester(focusRequest),
        enabled = isEnabled,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        value = searchQuery,
        onValueChange = onSearchQueryChanged,
        singleLine = true,
        shape = MaterialTheme.shapes.small,
        placeholder = { Text(stringResource(R.string.search_placeholder), style = MaterialTheme.typography.titleMedium) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        leadingIcon = {
            Icon(
                painter = painterResource(MyIcons.TwoTone.search),
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    )

}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    Anifox_AndroidTheme(useDarkTheme = false) {
        SearchBoxField()
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    Anifox_AndroidTheme(useDarkTheme = true) {
        SearchBoxField()
    }
}