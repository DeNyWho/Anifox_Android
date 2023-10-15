package club.anifox.android.domain.repository

import club.anifox.android.domain.model.user.account.TokenPair

interface IDataStoreRepository {
    suspend fun getToken(): TokenPair?
    suspend fun logout()
    suspend fun updateSession(access: String, refresh: String)
}
