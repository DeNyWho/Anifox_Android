package club.anifox.android.di

import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import club.anifox.android.data.datastore.UserDataSource
import club.anifox.android.data.datastore.repository.UserDataRepository
import club.anifox.android.data.datastore.serializer.LocalStorageSerializer
import club.anifox.android.data.local.dao.anime.AnimeDao
import club.anifox.android.data.local.db.AnifoxDatabase
import club.anifox.android.data.network.service.AnimeService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val dataModule = module {
    single { AnifoxDatabase.getInstance(get())}
    singleOf(::UserDataRepository)
    singleOf(::UserDataSource)
    singleOf(::LocalStorageSerializer)

    single { get<AnifoxDatabase>().animeDao() }

    singleOf(::AnimeService)

    single {
        DataStoreFactory.create(
            serializer = get<LocalStorageSerializer>(),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            migrations = listOf(),
        ) {
            androidContext().dataStoreFile("user_data.pb")
        }
    }
}
