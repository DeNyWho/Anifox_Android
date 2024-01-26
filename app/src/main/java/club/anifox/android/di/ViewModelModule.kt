package club.anifox.android.di

import club.anifox.android.presentation.screens.detail.DetailViewModel
import club.anifox.android.presentation.screens.home.HomeViewModel
import club.anifox.android.presentation.screens.player.PlayerViewModel
import club.anifox.android.presentation.screens.schedule.ScheduleViewModel
import club.anifox.android.presentation.screens.search.SearchViewModel
import club.anifox.android.presentation.screens.signin.SignInViewModel
import club.anifox.android.presentation.screens.signup.SignUpViewModel
import club.anifox.android.presentation.screens.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { SignInViewModel() }
    viewModel { SignUpViewModel() }
    viewModel { SearchViewModel() }
    viewModel { ScheduleViewModel() }
    viewModel { PlayerViewModel() }
    viewModel { DetailViewModel() }
}
