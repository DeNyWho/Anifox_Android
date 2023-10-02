package club.anifox.android.domain.enums

import club.anifox.android.presentation.common.ui.icons.MyIcons

enum class BottomNavTabs(val label: String, val icon: Int) {
    Home("Главная", MyIcons.Outlined.home),
    Browse("Поиск", MyIcons.TwoTone.search),
    Favourite("Избранное", MyIcons.Outlined.favourite),
    Profile("Профиль", MyIcons.Outlined.profile),
}
