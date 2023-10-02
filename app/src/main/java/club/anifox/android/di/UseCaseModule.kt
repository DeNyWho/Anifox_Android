package club.anifox.android.di

import club.anifox.android.domain.usecase.anime.GetAnimeDetailsUseCase
import club.anifox.android.domain.usecase.anime.GetAnimePagingUseCase
import club.anifox.android.domain.usecase.anime.GetAnimeRelatedUseCase
import club.anifox.android.domain.usecase.anime.GetAnimeScreenShotsUseCase
import club.anifox.android.domain.usecase.anime.GetAnimeSimilarUseCase
import club.anifox.android.domain.usecase.anime.GetAnimeUseCase
import club.anifox.android.domain.usecase.anime.GetAnimeUsersStatusUseCase
import club.anifox.android.domain.usecase.auth.AuthenticationUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val useCaseModule = module {
    singleOf(::GetAnimeUseCase)
    singleOf(::GetAnimePagingUseCase)
    singleOf(::GetAnimeDetailsUseCase)
    singleOf(::GetAnimeSimilarUseCase)
    singleOf(::GetAnimeRelatedUseCase)
    singleOf(::GetAnimeUsersStatusUseCase)
    singleOf(::GetAnimeScreenShotsUseCase)
    singleOf(::GetAnimeScreenShotsUseCase)

    singleOf(::AuthenticationUseCase)
}
