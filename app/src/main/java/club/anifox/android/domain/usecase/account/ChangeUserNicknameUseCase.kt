package club.anifox.android.domain.usecase.account

import club.anifox.android.data.remote.account.AccountService
import club.anifox.android.domain.model.common.Resource
import club.anifox.android.domain.state.StateListWrapper
import io.ktor.http.Cookie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ChangeUserNicknameUseCase(
    private val service: AccountService,
) {
    operator fun invoke(nickname: String): Flow<StateListWrapper<Cookie>> {
        return flow {
            emit(StateListWrapper.loading())
            println("ASD SADFASDAS")
            val state = when (val animeResult = service.changeUserNickName(nickname = nickname)) {
                is Resource.Success -> {
                    println("WERASD")
                    val data = animeResult.cookies
                    StateListWrapper(data)
                }
                is Resource.Error -> {
                    println("GFDSGFSD")
                    StateListWrapper(error = animeResult.error)
                }
                is Resource.Loading -> {
                    StateListWrapper.loading()
                }
            }

            emit(state)
        }.flowOn(Dispatchers.IO)
    }
}
