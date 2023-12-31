package club.anifox.android.presentation.screens.registration

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    private val _email: MutableState<String> = mutableStateOf("")
    val email: MutableState<String> = _email

    private val _login: MutableState<String> = mutableStateOf("")
    val login: MutableState<String> = _login

    private val _nickname: MutableState<String> = mutableStateOf("")
    val nickname: MutableState<String> = _nickname

    private val _password: MutableState<String> = mutableStateOf("")
    val password: MutableState<String> = _password

    private val _confirmPassword: MutableState<String> = mutableStateOf("")
    val confirmPassword: MutableState<String> = _confirmPassword

    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
    }

    fun onLoginChanged(newLogin: String) {
        _login.value = newLogin
    }

    fun onNicknameChanged(newNickname: String) {
        _nickname.value = newNickname
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }

    fun onPasswordConfirmChanged(newPasswordConfirm: String) {
        _confirmPassword.value = newPasswordConfirm
    }
}
