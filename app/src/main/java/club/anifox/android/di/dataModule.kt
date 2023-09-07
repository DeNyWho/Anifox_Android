package club.anifox.android.di

import club.anifox.android.data.local.database.AniFoxDataBase
import club.anifox.android.data.repository.RemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val dataModule = module {
    single { AniFoxDataBase.getInstance(get()) }
    singleOf(::RemoteDataSource)
}