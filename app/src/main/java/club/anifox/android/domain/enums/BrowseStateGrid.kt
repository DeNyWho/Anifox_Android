package club.anifox.android.domain.enums

import club.anifox.android.presentation.common.ui.icons.MyIcons

enum class BrowseStateGrid (val label: String, val icon: Int, val onClick: () -> Unit) {
    Popular("Популярное", MyIcons.Outlined.popular, {}),
    Filter("Фильтр", MyIcons.Outlined.filter, {}),
    Random("Рандом", MyIcons.Outlined.random, {}),
    Calendar("Расписание", MyIcons.Outlined.calendar, {}),
}