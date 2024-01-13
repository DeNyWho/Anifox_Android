package club.anifox.android.di

import club.anifox.android.presentation.screens.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
}