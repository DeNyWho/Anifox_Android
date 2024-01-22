package club.anifox.android.di

import club.anifox.android.domain.usecase.anime.AnimeUseCase
import club.anifox.android.domain.usecase.user.TokenUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val useCaseModule = module {
    singleOf(::TokenUseCase)
    singleOf(::AnimeUseCase)
}
