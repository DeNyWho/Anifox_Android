package club.anifox.android.presentation.screens.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import club.anifox.android.R
import club.anifox.android.domain.model.anime.AnimeUsersStatus
import club.anifox.android.domain.model.anime.isAllZero
import club.anifox.android.domain.state.StateWrapper
import club.anifox.android.presentation.common.ui.theme.blueLight
import club.anifox.android.presentation.common.ui.theme.green
import club.anifox.android.presentation.common.ui.theme.lightGrey
import club.anifox.android.presentation.common.ui.theme.orange
import club.anifox.android.presentation.common.ui.theme.purple
import com.himanshoe.charty.common.toChartDataCollection
import com.himanshoe.charty.pie.PieChart
import com.himanshoe.charty.pie.config.PieChartConfig
import com.himanshoe.charty.pie.model.PieData

@Composable
fun DetailChart(data: StateWrapper<AnimeUsersStatus>) {
    if(!data.isLoading && data.data != null) {
        val statuses = data.data

        Text(
            text = stringResource(R.string.chart_users_status),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 8.dp),
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )

        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
        ) {
            if(statuses.isAllZero()) {
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    StatusRow(stringResource(R.string.status_zero), lightGrey)
                }
            } else {
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    StatusRow(stringResource(R.string.status_watching), purple)
                    StatusRow(stringResource(R.string.status_in_plan), blueLight)
                    StatusRow(stringResource(R.string.status_watched), green)
                    StatusRow(stringResource(R.string.status_postponed), orange)
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    StatusRow("${statuses.watching}", null, needBox = false)
                    StatusRow("${statuses.inPlan}", null, needBox = false)
                    StatusRow("${statuses.watched}", null, needBox = false)
                    StatusRow("${statuses.postponed}", null, needBox = false)
                }
            }
            val chartData =
                if (statuses.isAllZero()) {
                    listOf(
                        PieData(1f, "", lightGrey)
                    )
                } else {
                    listOf(
                        PieData(statuses.watching.toFloat(), "", purple),
                        PieData(statuses.inPlan.toFloat(), "", blueLight),
                        PieData(statuses.watched.toFloat(), "", green),
                        PieData(statuses.postponed.toFloat(), "", orange),
                    )
                }
            Spacer(modifier = Modifier.weight(1f))
            PieChart(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(140.dp),
                dataCollection = chartData.toChartDataCollection(),
                pieChartConfig = PieChartConfig(
                    donut = true,
                    showLabel = false
                )
            )
        }
    }
}

@Composable
private fun StatusRow(
    statusText: String,
    backgroundColor: Color?,
    needBox: Boolean = true,
    textVisible: Boolean = true
) {
    Row(
        modifier = Modifier
            .height(36.dp)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(needBox) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(backgroundColor!!)
                    .clip(MaterialTheme.shapes.small)
            )
        }
        if(textVisible) {
            Text(
                modifier = Modifier
                    .padding(start = 8.dp),
                text = statusText,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview
@Composable
private fun DetailChartPreviewWithData() {
    DetailChart(
        data = StateWrapper(
            data = AnimeUsersStatus(
                watching = 4,
                inPlan = 1,
                watched = 3,
                postponed = 1
            )
        )
    )
}

@Preview
@Composable
private fun DetailChartPreviewNoData() {
    DetailChart(
        data = StateWrapper(
            data = AnimeUsersStatus(
                watching = 0,
                inPlan = 0,
                watched = 0,
                postponed = 0
            )
        )
    )
}