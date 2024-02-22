package club.anifox.android.presentation.util

import club.anifox.android.domain.model.anime.AnimeSeason
import java.time.LocalDateTime
import java.time.Month

fun LocalDateTime.takeCurrentSeason(): AnimeSeason {
    return when(this.month) {
        Month.DECEMBER, Month.JANUARY, Month.FEBRUARY -> AnimeSeason.Winter
        Month.MARCH, Month.APRIL, Month.MAY -> AnimeSeason.Spring
        Month.JUNE, Month.JULY, Month.AUGUST -> AnimeSeason.Summer
        else -> AnimeSeason.Fall
    }
}
