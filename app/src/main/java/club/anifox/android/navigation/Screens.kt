package club.anifox.android.navigation

sealed class Screens(val route: String) {
    data object Splash : Screens("splash_screen")
    data object SignIn : Screens("sign_in_screen")
    data object SignUp : Screens("sign_up_screen")
    data object Home : Screens("home_screen")
    data object Detail : Screens("detail_screen")
    data object Browse : Screens("browse_screen")
    data object Search : Screens("search_screen")
    data object Schedule : Screens("schedule_screen")
    data object Profile : Screens("profile_screen")
    data object Player : Screens("player_screen")
}
