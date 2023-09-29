package club.anifox.android.di

import club.anifox.android.data.remote.anime.AnimeService
import club.anifox.android.data.remote.auth.AuthService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val networkModule = module {
    singleOf(::AnimeService)
    singleOf(::AuthService)
}