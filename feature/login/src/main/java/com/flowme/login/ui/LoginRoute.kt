package com.flowme.login.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.flowme.login.R
import com.flowme.login.ui_logic.LoginViewModel

@Composable
fun LoginRoute(
    onGoToHome: () -> Unit,
    viewModel: LoginViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val loginUnsuccessfulToastMessage = stringResource(R.string.login_unsuccessful_toast_message)

    LoginScreen(
        onLoginWithGoogle = {
            viewModel.loginWithGoogle(
                onGoToHome,
                onFailure = {
                    Toast
                        .makeText(context, loginUnsuccessfulToastMessage, Toast.LENGTH_SHORT)
                        .show()
                }
            )
        },
        modifier = modifier
    )
}