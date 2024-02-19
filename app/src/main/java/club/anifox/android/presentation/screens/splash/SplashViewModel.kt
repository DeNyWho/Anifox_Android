package club.anifox.android.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import club.anifox.android.domain.model.user.UserSession
import club.anifox.android.domain.usecase.user.TokenUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class SplashViewModel (
    tokenUseCase: TokenUseCase,
) : ViewModel () {

    private val token = tokenUseCase.invoke().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UserSession()
    )

    fun redirect(navigateToHome: () -> Unit, navigateToSignIn: () -> Unit) {
        if(token.value.refreshToken.isNotEmpty()) {
            navigateToSignIn.invoke()
        } else {
            navigateToHome.invoke()
        }
    }
}
