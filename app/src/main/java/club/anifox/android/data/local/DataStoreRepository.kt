package club.anifox.android.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import club.anifox.android.common.Constants.ACCESS_TOKEN_KEY
import club.anifox.android.common.Constants.REFRESH_TOKEN_KEY
import club.anifox.android.data.repository.IDataStoreRepository
import kotlinx.coroutines.flow.first

class DataStoreRepository(private val context: Context): IDataStoreRepository {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("anifox_manager")

    private object PreferencesKey {
        val accessToken = stringPreferencesKey(ACCESS_TOKEN_KEY)
        val refreshToken = stringPreferencesKey(REFRESH_TOKEN_KEY)
    }

    override suspend fun updateSession(access: String, refresh: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKey.accessToken] = access
            preferences[PreferencesKey.refreshToken] = refresh
        }
    }

    override suspend fun getToken(): String? {
        val preferences = context.dataStore.data.first()
        return preferences[PreferencesKey.accessToken]
    }

    override suspend fun logout() {
        context.dataStore.edit {
            it.clear()
        }
    }
}