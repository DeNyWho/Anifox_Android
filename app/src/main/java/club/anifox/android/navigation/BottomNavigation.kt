package club.anifox.android.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import club.anifox.android.domain.enums.BottomNavTabs
import club.anifox.android.presentation.common.ui.theme.Anifox_AndroidTheme
import club.anifox.android.presentation.screens.browse.BrowseScreen
import club.anifox.android.presentation.screens.favourite.FavouriteScreen
import club.anifox.android.presentation.screens.home.HomeScreen
import club.anifox.android.presentation.screens.profile.ProfileScreen

@Composable
fun BottomNavigation(
    tabs: MutableState<BottomNavTabs>,
    navController: NavHostController,
) {
    Scaffold(
        bottomBar = { Content(tabs = tabs) },
    ) {
        val modifier = Modifier.padding(it)
        when (tabs.value) {
            BottomNavTabs.Home -> HomeScreen(
                modifier = modifier,
                navController = navController,
            )
            BottomNavTabs.Browse -> BrowseScreen(
                modifier = modifier,
                navController = navController,
            )
            BottomNavTabs.Favourite -> FavouriteScreen(
                modifier = modifier,
                navController = navController,
            )
            BottomNavTabs.Profile -> ProfileScreen(
                modifier = modifier,
                navController = navController,
            )
        }
    }
}

@Composable
private fun Content(tabs: MutableState<BottomNavTabs>) {
    NavigationBar(
        modifier = Modifier.height(72.dp),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
    ) {
        for (tab in BottomNavTabs.values()) {
            NavigationBarItem(
                alwaysShowLabel = false,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(5f),
                selected = tabs.value == tab,
                onClick = {
                    if (tabs.value == tab) return@NavigationBarItem
                    tabs.value = tab
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.background,
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                ),
                label = {
                    Text(
                        text = tab.label,
                        style = MaterialTheme.typography.labelMedium,
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(id = tab.icon),
                        contentDescription = tab.label,
                        modifier = Modifier.size(25.dp),
                    )
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    Anifox_AndroidTheme(useDarkTheme = false) {
        val selectedTab = remember { mutableStateOf(BottomNavTabs.Home) }
        Content(
            selectedTab,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    Anifox_AndroidTheme(useDarkTheme = true) {
        val selectedTab = remember { mutableStateOf(BottomNavTabs.Profile) }
        Content(
            selectedTab,
        )
    }
}
