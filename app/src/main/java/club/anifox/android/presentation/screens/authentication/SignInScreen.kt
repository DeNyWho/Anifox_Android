package club.anifox.android.presentation.screens.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import club.anifox.android.R
import club.anifox.android.navigation.Screens
import club.anifox.android.presentation.common.ui.theme.Anifox_AndroidTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun SignInScreen(
    navController: NavHostController,
    viewModel: SignInViewModel = getViewModel(),
) {
    viewModel.test()
    Content(
        onUserIdentifierChanged = { newLogin ->
            viewModel.onUserIdentifierChanged(newLogin)
        },
        loginState = viewModel.userIdentifier.value,
        onPasswordChanged = { newPassword ->
            viewModel.onPasswordChanged(newPassword)
        },
        passwordState = viewModel.password.value,
        onAuthenticationClick = {
            viewModel.authentication { navController.navigate(Screens.Home.route) }
        },
        navigateToSignUp = {
            navController.navigate(Screens.SignUp.route)
        },
        navigateToHome = {
            navController.navigate(Screens.Home.route)
        },
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Content(
    onUserIdentifierChanged: (String) -> Unit,
    loginState: String,
    onPasswordChanged: (String) -> Unit,
    passwordState: String,
    onAuthenticationClick: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
) {
    var passwordVisible by remember { mutableStateOf(false) }
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
                    text = stringResource(R.string.sign_in_start),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(40.dp)
                        .align(Alignment.CenterHorizontally),
                )

                OutlinedTextField(
                    value = loginState,
                    onValueChange = { newLogin -> onUserIdentifierChanged(newLogin) },
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .height(60.dp),
                    singleLine = true,
                    label = { Text(stringResource(R.string.hint_login_or_email), style = MaterialTheme.typography.titleMedium) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        },
                    ),
                )

                OutlinedTextField(
                    value = passwordState,
                    onValueChange = { newPassword -> onPasswordChanged(newPassword) },
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 12.dp)
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

                Button(
                    onClick = {
                        onAuthenticationClick.invoke()
                    },
                    shape = MaterialTheme.shapes.small,
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
                        text = stringResource(R.string.sign_in_button_auth),
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Divider(
                    Modifier.weight(1f),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                )

                Text(
                    text = stringResource(R.string.chose_or_sing_in),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(20.dp),
                )

                Divider(
                    Modifier.weight(1f),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }

            Column {
                // add this part when the shikimori is not blocked
//                Button(
//                    onClick = {
//                    },
//                    shape = MaterialTheme.shapes.small,
//                    colors = ButtonDefaults.textButtonColors(
//                        containerColor = Color.Transparent,
//                        contentColor = MaterialTheme.colorScheme.onBackground
//                    ),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .border(
//                            width = 1.dp,
//                            color = MaterialTheme.colorScheme.onBackground,
//                            shape = RoundedCornerShape(10.dp)
//                        )
//                        .height(50.dp)
//                ) {
//                    Icon(
//                        painter = painterResource(MyIcons.Outlined.shikimori),
//                        contentDescription = null,
//                        modifier = Modifier.padding(end = 4.dp)
//                    )
//                    Text(text = stringResource(R.string.shikimori_auth), fontSize = 20.sp)
//                }
                Button(
                    onClick = {
                        navigateToHome.invoke()
                    },
                    shape = MaterialTheme.shapes.small,
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onBackground,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
//                        .padding(vertical = 16.dp)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = RoundedCornerShape(10.dp),
                        )
                        .height(50.dp),
                ) {
                    Text(text = stringResource(R.string.skip_auth), fontSize = 20.sp)
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
                text = stringResource(R.string.have_not_account),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium,
            )

            Text(
                text = stringResource(R.string.sign_up_navigate),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.clickable {
                    navigateToSignUp.invoke()
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
            onUserIdentifierChanged = {},
            loginState = "",
            onPasswordChanged = {},
            passwordState = "",
            onAuthenticationClick = {},
            navigateToSignUp = {},
            navigateToHome = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    Anifox_AndroidTheme(useDarkTheme = true) {
        Content(
            onUserIdentifierChanged = {},
            loginState = "",
            onPasswordChanged = {},
            passwordState = "",
            onAuthenticationClick = {},
            navigateToSignUp = {},
            navigateToHome = {},
        )
    }
}
