package me.floow.login.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import me.floow.login.ui_logic.LoginViewModel
import me.flowme.login.R

@Composable
fun LoginRoute(
    onGoToHome: () -> Unit,
    viewModel: LoginViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val loginUnsuccessfulToastMessage = stringResource(R.string.login_unsuccessful_toast_message)
    val state by viewModel.loginState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initialize(
            onGoToHome,
            onFailure = {
                Toast
                    .makeText(context, loginUnsuccessfulToastMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        )
    }

    LoginScreen(
        state = state,
        onLoginWithGoogle = viewModel::loginWithGoogle,
        modifier = modifier
    )
}