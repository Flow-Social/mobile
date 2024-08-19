package com.flowme.flow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.flowme.api.AuthApiImpl
import com.flowme.auth.AuthenticationManagerImpl
import com.flowme.auth.GoogleOAuthImpl
import com.flowme.auth.models.GoogleOAuthInfo
import com.flowme.flow.ui.App
import com.flowme.login.ui_logic.LoginViewModel
import com.flowme.uikit.theme.FlowTheme

// TODO: remove VM instantiations when there will be DI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Make build environments for secrets
        val googleOAuthImpl = GoogleOAuthImpl(
            context = this,
            googleOAuthInfo = GoogleOAuthInfo(
                serverClientId = "529714055602-brug0jk1196as3q7mns0l4bna99phrac.apps.googleusercontent.com"
            )
        )

        val authenticationManager = AuthenticationManagerImpl(
            this@MainActivity,
            googleOAuth = googleOAuthImpl,
            authApi = AuthApiImpl()
        )

        val loginViewModel = LoginViewModel(
            authenticationManager = authenticationManager,
        )

        setContent {
            FlowTheme {
                App(loginViewModel, Modifier.fillMaxSize())
            }
        }
    }
}