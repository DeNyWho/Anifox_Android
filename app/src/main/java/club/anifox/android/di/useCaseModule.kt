package club.anifox.android.di

import club.anifox.android.domain.usecase.anime.GetAnimeUseCase
import club.anifox.android.domain.usecase.anime.GetAnimePagingUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val useCaseModule = module {
    singleOf(::GetAnimeUseCase)
    singleOf(::GetAnimePagingUseCase)
}