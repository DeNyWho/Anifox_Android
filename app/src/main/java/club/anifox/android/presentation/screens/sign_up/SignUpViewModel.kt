package club.anifox.android.presentation.screens.sign_up

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel() {
    private val _login: MutableState<String> = mutableStateOf("")
    val login: MutableState<String> = _login

    private val _password: MutableState<String> = mutableStateOf("")
    val password: MutableState<String> = _password

    fun onLoginChanged(newLogin: String){
        _login.value = newLogin
    }

    fun onPasswordChanged(newPassword: String){
        _password.value = newPassword
    }


}