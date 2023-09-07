package club.anifox.android.di

import club.anifox.android.presentation.screens.browse.BrowseViewModel
import club.anifox.android.presentation.screens.detail.DetailViewModel
import club.anifox.android.presentation.screens.favourite.FavouriteViewModel
import club.anifox.android.presentation.screens.home.HomeViewModel
import club.anifox.android.presentation.screens.profile.ProfileViewModel
import club.anifox.android.presentation.screens.search.SearchViewModel
import club.anifox.android.presentation.screens.sign_in.SignInViewModel
import club.anifox.android.presentation.screens.sign_up.SignUpViewModel
import club.anifox.android.presentation.screens.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { SignInViewModel() }
    viewModel { SignUpViewModel() }
    viewModel { HomeViewModel() }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { BrowseViewModel(get()) }
    viewModel { FavouriteViewModel() }
    viewModel { ProfileViewModel() }
}