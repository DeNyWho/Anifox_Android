package club.anifox.android.presentation.screens.authentication

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import club.anifox.android.domain.usecase.account.ChangeUserNicknameUseCase
import club.anifox.android.domain.usecase.auth.AuthenticationUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SignInViewModel(
    private val authenticationUseCase: AuthenticationUseCase,
    private val changeUserNicknameUseCase: ChangeUserNicknameUseCase,
) : ViewModel() {
    private val _userIdentifier: MutableState<String> = mutableStateOf("")
    val userIdentifier: MutableState<String> = _userIdentifier

    private val _password: MutableState<String> = mutableStateOf("")
    val password: MutableState<String> = _password

    fun test() {
        changeUserNicknameUseCase.invoke("11").onEach { value ->
        }.launchIn(viewModelScope)
        println()
    }

    fun onUserIdentifierChanged(newLogin: String) {
        _userIdentifier.value = newLogin
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }

    fun authentication(navigate: () -> Unit) {
        authenticationUseCase.invoke(userIdentifier.value, password.value).onEach { value ->
            if (value.data == true) navigate.invoke()
        }.launchIn(viewModelScope)
    }
}
