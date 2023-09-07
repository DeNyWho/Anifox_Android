package club.anifox.android.navigation

sealed class Screens(val route: String) {
    object Splash: Screens("splash_screen")
    object SignIn: Screens("sign_in_screen")
    object SignUp: Screens("sign_up_screen")
    object Home: Screens("home_screen")
    object Detail: Screens("detail_screen")
    object Browse: Screens("browse_screen")
    object Search: Screens("search_screen")
    object Favourite: Screens("favourite_screen")
    object Profile: Screens("profile_screen")
}