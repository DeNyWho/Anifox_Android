package club.anifox.android.data.datastore

import androidx.datastore.core.DataStore
import club.anifox.android.domain.model.user.UserAccount
import club.anifox.android.domain.model.user.UserSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataSource(
    private val dataStore: DataStore<UserAccount>
) {
    val userData = buildMap<String,Flow<*>> {
        put("session", dataStore.data.map { it.session })
    }

    suspend fun updateSession(session: UserSession) {
        dataStore.updateData { userData ->
            userData.copy(
                session = session
            )
        }
    }

    suspend fun logout() {
        dataStore.updateData { userData ->
            userData.copy(
                session = UserSession()
            )
        }
    }


}