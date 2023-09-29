package club.anifox.android.domain.usecase.auth

import club.anifox.android.data.remote.auth.AuthService
import club.anifox.android.domain.model.common.Resource
import club.anifox.android.domain.state.StateWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class AuthenticationUseCase(private val authService: AuthService) {

    operator fun invoke(userIdentifier: String, password: String): Flow<StateWrapper<String>> {
        return flow {
            emit(StateWrapper.loading())

            val state = when(val result = authService.authentication(userIdentifier, password)) {
                is Resource.Success -> {
                    val cookie = result.cookies
                    println(cookie)
                    StateWrapper("")
                }
                is Resource.Error -> {
                    StateWrapper(error = result.error)
                }
                is Resource.Loading -> {
                    StateWrapper.loading()
                }
            }

            emit(state)
        }
    }
}