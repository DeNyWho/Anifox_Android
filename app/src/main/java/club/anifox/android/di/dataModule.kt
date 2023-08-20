package club.anifox.android.di

import club.anifox.android.data.local.DataStoreRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val dataModule = module {
    singleOf(::DataStoreRepository)
}