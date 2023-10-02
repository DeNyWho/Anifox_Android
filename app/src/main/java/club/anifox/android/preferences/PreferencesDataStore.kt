package club.anifox.android.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import club.anifox.android.core.DataStoreConstants
import club.anifox.android.domain.repository.IDataStoreRepository
import io.ktor.http.Cookie
import kotlinx.coroutines.flow.first

class PreferencesDataStore(context: Context) : IDataStoreRepository {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("anifox_manager")
    private val dataStore = context.dataStore

    override suspend fun updateSession(access: Cookie, refresh: Cookie) {
        dataStore.edit { preferences ->
            preferences[accessToken] = access.value
            preferences[refreshToken] = refresh.value
        }
    }

    override suspend fun getToken(): String? {
        val preferences = dataStore.data.first()
        return preferences[accessToken]
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
