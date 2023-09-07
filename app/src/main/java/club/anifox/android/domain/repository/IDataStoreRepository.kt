package club.anifox.android.domain.repository

interface IDataStoreRepository {
    suspend fun getToken(): String?
    suspend fun updateSession(access: String, refresh: String)
    suspend fun logout()
}