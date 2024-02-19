package club.anifox.android.presentation.components.horizontalContent.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HorizontalContentHeader(
    title: String,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .heightIn(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = onButtonClick
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "see all",
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }

    }

}
