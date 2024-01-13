package club.anifox.android.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import club.anifox.android.domain.usecase.TokenUseCase

internal val useCaseModule = module {
    singleOf(::TokenUseCase)
}