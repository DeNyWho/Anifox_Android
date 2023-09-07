package club.anifox.android.presentation.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatDateWithMonth(inputDate: LocalDate): String {
    val dayOfMonth = inputDate.dayOfMonth
    val month = inputDate.format(DateTimeFormatter.ofPattern("MMMM", Locale("ru")))
    val year = inputDate.year

    return "$dayOfMonth $month $year"
}