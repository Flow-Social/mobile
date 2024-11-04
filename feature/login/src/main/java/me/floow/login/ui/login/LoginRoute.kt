package me.floow.login.ui.login

import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import me.floow.login.uilogic.LoginViewModel
import me.floow.uikit.util.SetNavigationBarColor
import me.flowme.login.R

@Composable
fun LoginRoute(
    onGoToCreateProfile: () -> Unit,
    onGoToHome: () -> Unit,
    viewModel: LoginViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val loginUnsuccessfulToastMessage = stringResource(R.string.login_unsuccessful_toast_message)
    val state by viewModel.loginState.collectAsState()

    SetNavigationBarColor(MaterialTheme.colorScheme.background)

    LaunchedEffect(Unit) {
        viewModel.initialize(
            onGoToCreateProfile = onGoToCreateProfile,
            onGoToHome = onGoToHome,
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