package club.anifox.android.di

import club.anifox.android.data.remote.AuthInterceptor
import club.anifox.android.data.remote.account.AccountService
import club.anifox.android.data.remote.anime.AnimeService
import club.anifox.android.data.remote.auth.AuthService
import club.anifox.android.data.remote.user.UserService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val networkModule = module {
    singleOf(::AnimeService)
    singleOf(::AccountService)
    singleOf(::UserService)
    singleOf(::AuthService)

    singleOf(::AuthInterceptor)
}
