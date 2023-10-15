package club.anifox.android.domain.usecase.auth

import club.anifox.android.data.remote.auth.AuthService
import club.anifox.android.domain.model.common.Resource
import club.anifox.android.domain.model.dto.auth.AuthenticationDto
import club.anifox.android.domain.state.StateWrapper
import club.anifox.android.preferences.PreferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthenticationUseCase(
    private val authService: AuthService,
    private val preferencesDataStore: PreferencesDataStore,
) {

    operator fun invoke(userIdentifier: String, password: String): Flow<StateWrapper<Boolean>> {
        return flow {
            emit(StateWrapper.loading())

            val state = when (
                val result =
                    authService.authentication(AuthenticationDto(userIdentifier, password))
            ) {
                is Resource.Success -> {
                    val cookies = result.cookies

                    val accessCookie = cookies?.find { it.name == "access_token" }
                    val refreshCookie = cookies?.find { it.name == "refresh_token" }

                    if (accessCookie != null && refreshCookie != null) {
                        preferencesDataStore.updateSession(access = accessCookie.value, refresh = refreshCookie.value)
                        StateWrapper(true)
                    } else {
                        StateWrapper(false)
                    }
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
