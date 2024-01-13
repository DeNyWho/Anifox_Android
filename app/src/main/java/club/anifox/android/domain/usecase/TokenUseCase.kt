package club.anifox.android.domain.usecase

import club.anifox.android.data.datastore.repository.UserDataRepository
import club.anifox.android.domain.model.user.UserSession
import kotlinx.coroutines.flow.Flow

class TokenUseCase (
    private val userDataRepository: UserDataRepository
) {
    operator fun invoke(): Flow<UserSession> {
        return userDataRepository.session
    }
}