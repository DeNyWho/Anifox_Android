package club.anifox.android.di

import club.anifox.android.domain.usecase.anime.GetAnimeUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val useCaseModule = module {
    singleOf(::GetAnimeUseCase)
}