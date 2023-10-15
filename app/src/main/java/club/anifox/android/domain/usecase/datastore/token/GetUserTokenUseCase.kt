package club.anifox.android.domain.usecase.datastore.token

import club.anifox.android.domain.model.user.account.TokenPair
import club.anifox.android.domain.state.StateWrapper
import club.anifox.android.preferences.PreferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserTokenUseCase(private val preferencesDataStore: PreferencesDataStore) {
    operator fun invoke(): Flow<StateWrapper<TokenPair>> {
        return flow {
            emit(StateWrapper.loading())

            val tokenPair = preferencesDataStore.getToken()

            val state = StateWrapper(tokenPair)

            emit(state)
        }
    }
}
