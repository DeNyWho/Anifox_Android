package club.anifox.android.presentation.screens.sign_up

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import club.anifox.android.R
import club.anifox.android.navigation.Screens
import club.anifox.android.presentation.common.ui.theme.Anifox_AndroidTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: SignUpViewModel = getViewModel(),
) {
    Content(
        onEmailChanged = { newEmail ->
            viewModel.onEmailChanged(newEmail)
        },
        onLoginChanged = { newLogin ->
            viewModel.onLoginChanged(newLogin)
        },
        onNicknameChanged = { newLogin ->
            viewModel.onNicknameChanged(newLogin)
        },
        onPasswordChanged = { newPassword ->
            viewModel.onPasswordChanged(newPassword)
        },
        onPasswordConfirmChanged = { newPassword ->
            viewModel.onPasswordConfirmChanged(newPassword)
        },
        emailState = viewModel.email.value,
        loginState = viewModel.login.value,
        nicknameState = viewModel.nickname.value,
        passwordState = viewModel.password.value,
        passwordConfirmState = viewModel.confirmPassword.value,
        navigateToSignIn = {
            navController.navigate(Screens.SignIn.route)
        },
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Content(
    onEmailChanged: (String) -> Unit,
    onLoginChanged: (String) -> Unit,
    onNicknameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordConfirmChanged: (String) -> Unit,
    loginState: String,
    nicknameState: String,
    emailState: String,
    passwordState: String,
    passwordConfirmState: String,
    navigateToSignIn: () -> Unit,
) {
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordConfirmVisible by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = null, // this gets rid of the ripple effect
        ) {
            keyboardController?.hide()
            focusManager.clearFocus(true)
        },
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 30.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Column {
                Text(
                    text = stringResource(R.string.sign_up_start),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 36.dp)
                        .align(Alignment.CenterHorizontally),
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    OutlinedTextField(
                        value = emailState,
                        onValueChange = { newEmail -> onEmailChanged(newEmail) },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .height(60.dp),
                        singleLine = true,
                        label = { Text(stringResource(R.string.hint_email), style = MaterialTheme.typography.titleMedium) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    )

                    OutlinedTextField(
                        value = loginState,
                        onValueChange = { newLogin -> onLoginChanged(newLogin) },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .height(60.dp),
                        singleLine = true,
                        label = { Text(stringResource(R.string.hint_login), style = MaterialTheme.typography.titleMedium) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    )

                    OutlinedTextField(
                        value = nicknameState,
                        onValueChange = { newNickname -> onNicknameChanged(newNickname) },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .height(60.dp),
                        singleLine = true,
                        label = { Text(stringResource(R.string.hint_nickname), style = MaterialTheme.typography.titleMedium) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    )

                    OutlinedTextField(
                        value = passwordState,
                        onValueChange = { newPassword -> onPasswordChanged(newPassword) },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .height(60.dp),
                        trailingIcon = {
                            val image = if (passwordVisible) {
                                Icons.Filled.Visibility
                            } else {
                                Icons.Filled.VisibilityOff
                            }

                            val description = if (passwordVisible) "Hide password" else "Show password"

                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = image, contentDescription = description)
                            }
                        },
                        singleLine = true,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        label = { Text(stringResource(R.string.hint_password), style = MaterialTheme.typography.titleMedium) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    )

                    OutlinedTextField(
                        value = passwordConfirmState,
                        onValueChange = { newPasswordConfirm -> onPasswordConfirmChanged(newPasswordConfirm) },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .height(60.dp),
                        trailingIcon = {
                            val image = if (passwordConfirmVisible) {
                                Icons.Filled.Visibility
                            } else {
                                Icons.Filled.VisibilityOff
                            }

                            val description = if (passwordConfirmVisible) "Hide password" else "Show password"

                            IconButton(onClick = { passwordConfirmVisible = !passwordConfirmVisible }) {
                                Icon(imageVector = image, contentDescription = description)
                            }
                        },
                        singleLine = true,
                        visualTransformation = if (passwordConfirmVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        label = { Text(stringResource(R.string.hint_password_confirm), style = MaterialTheme.typography.titleMedium) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    )
                }

                Button(
                    onClick = {
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onBackground,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 28.dp)
                        .height(50.dp),
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 4.dp,
                        disabledElevation = 0.dp,
                    ),
                ) {
                    Text(
                        text = stringResource(R.string.sign_up_button_auth),
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .heightIn(min = 50.dp, max = 200.dp)
                    .weight(1f),
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(
                space = 4.dp,
                alignment = Alignment.CenterHorizontally,
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.have_account),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium,
            )

            Text(
                text = stringResource(R.string.sign_in_button_auth),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.clickable {
                    navigateToSignIn.invoke()
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    Anifox_AndroidTheme(useDarkTheme = false) {
        Content(
            onEmailChanged = {},
            onLoginChanged = {},
            onNicknameChanged = {},
            onPasswordChanged = {},
            onPasswordConfirmChanged = {},
            emailState = "",
            loginState = "",
            nicknameState = "",
            passwordState = "",
            passwordConfirmState = "",
            navigateToSignIn = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    Anifox_AndroidTheme(useDarkTheme = true) {
        Content(
            onEmailChanged = {},
            onLoginChanged = {},
            onNicknameChanged = {},
            onPasswordChanged = {},
            onPasswordConfirmChanged = {},
            emailState = "",
            loginState = "",
            nicknameState = "",
            passwordState = "",
            passwordConfirmState = "",
            navigateToSignIn = {},
        )
    }
}
