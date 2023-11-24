package club.anifox.android.di

import club.anifox.android.data.datastore.UserDataSource
import club.anifox.android.data.datastore.repository.UserDataRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val dataModule = module {
    singleOf(::UserDataRepository)
    singleOf(::UserDataSource)
}