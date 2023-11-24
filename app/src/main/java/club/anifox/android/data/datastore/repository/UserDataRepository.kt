package club.anifox.android.data.datastore.repository

import club.anifox.android.data.datastore.UserDataSource
import club.anifox.android.domain.user.UserSession
import kotlinx.coroutines.flow.Flow

class UserDataRepository(
    private val userDataSource: UserDataSource,
) {
    val session: Flow<UserSession> by userDataSource.userData

    suspend fun updateSession(session: UserSession) =
        userDataSource.updateSession(session)
}