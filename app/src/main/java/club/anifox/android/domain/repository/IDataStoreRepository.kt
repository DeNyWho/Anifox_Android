package club.anifox.android.domain.repository

import io.ktor.http.Cookie

interface IDataStoreRepository {
    suspend fun getToken(): String?
    suspend fun logout()
    suspend fun updateSession(access: Cookie, refresh: Cookie)
}
