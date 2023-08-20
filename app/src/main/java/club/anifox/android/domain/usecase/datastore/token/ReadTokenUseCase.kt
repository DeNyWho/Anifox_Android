package club.anifox.android.domain.usecase.datastore.token

import club.anifox.android.data.local.DataStoreRepository
import club.anifox.android.data.local.safeDataStoreCall
import club.anifox.android.domain.state.StateWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ReadTokenUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    operator fun invoke(): Flow<StateWrapper<String>> {
        return flow {
            emit(StateWrapper.loading<String>())
            StateWrapper(
                isLoading = false,
                result = safeDataStoreCall {
                    dataStoreRepository.getToken()
                }
            )
        }.flowOn(Dispatchers.IO)
    }
}
