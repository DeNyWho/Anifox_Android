package club.anifox.android.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import club.anifox.android.core.DataStoreConstants
import club.anifox.android.domain.model.user.account.TokenPair
import club.anifox.android.domain.repository.IDataStoreRepository
import kotlinx.coroutines.flow.first

class PreferencesDataStore(context: Context) : IDataStoreRepository {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("anifox_manager")
    private val dataStore = context.dataStore

    override suspend fun updateSession(access: String, refresh: String) {
        dataStore.edit { preferences ->
            preferences[accessToken] = access
            preferences[refreshToken] = refresh
        }
    }

    override suspend fun getToken(): TokenPair? {
        val preferences = dataStore.data.first()

        val accessToken = preferences[accessToken]
        val refreshToken = preferences[refreshToken]

        return if (accessToken != null && refreshToken != null) {
            TokenPair(
                accessToken = accessToken,
                refreshToken = refreshToken,
            )
        } else {
            null
        }
    }

    override suspend fun logout() {
        dataStore.edit {
            it.clear()
        }
    }

    companion object {
        val accessToken = stringPreferencesKey(DataStoreConstants.ACCESS_TOKEN_KEY)
        val refreshToken = stringPreferencesKey(DataStoreConstants.REFRESH_TOKEN_KEY)
    }
}
