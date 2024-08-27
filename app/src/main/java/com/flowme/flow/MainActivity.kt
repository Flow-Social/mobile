package com.flowme.flow

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.flowme.api.AuthApiConfig
import com.flowme.api.AuthApiImpl
import com.flowme.auth.AuthenticationManagerImpl
import com.flowme.auth.GoogleOAuthImpl
import com.flowme.auth.models.GoogleOAuthInfo
import com.flowme.domain.auth.AuthenticationManager
import com.flowme.domain.auth.GoogleOAuth
import com.flowme.flow.ui.App
import com.flowme.login.ui_logic.LoginViewModel
import com.flowme.uikit.theme.FlowTheme
import kotlinx.coroutines.*
import java.util.*

// TODO: remove VM instantiations when there will be DI

class MainActivity : ComponentActivity() {
    private lateinit var googleOAuthImpl: GoogleOAuth
    private lateinit var authenticationManager: AuthenticationManager
    private lateinit var coroutineScope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        coroutineScope = CoroutineScope(CoroutineName("MainActivity"))

        // TODO: Make build environments for secrets
        googleOAuthImpl = GoogleOAuthImpl(
            context = this,
            googleOAuthInfo = GoogleOAuthInfo(
                clientId = "529714055602-vmcr4n7qe6rek70fgufrv3aklvu7ukvf.apps.googleusercontent.com",
                redirectUri = "com.flowme.flow:/"
            )
        )

        authenticationManager = AuthenticationManagerImpl(
            this@MainActivity,
            googleOAuth = googleOAuthImpl,
            authApi = AuthApiImpl(
                AuthApiConfig(apiUrl = "https://floow.me/api")
            )
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

    override fun onNewIntent(intent: Intent) {
        intent.data?.getQueryParameter("code")?.let { code ->
            coroutineScope.launch {
                authenticationManager.handleGoogleOAuthCode(code)
            }
        }

        super.onNewIntent(intent)
    }

    override fun attachBaseContext(newBase: Context?) {
        // TODO: remove at production
        val newConfiguration = Configuration(newBase?.resources?.configuration).apply {
            setLocale(Locale("ru"))
        }

        super.attachBaseContext(newBase?.createConfigurationContext(newConfiguration))
    }
}