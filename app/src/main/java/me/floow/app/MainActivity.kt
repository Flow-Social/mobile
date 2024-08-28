package me.floow.app

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import kotlinx.coroutines.*
import me.floow.api.AuthApiConfig
import me.floow.api.AuthApiImpl
import me.floow.app.ui.App
import me.floow.auth.AuthenticationManagerImpl
import me.floow.auth.GoogleOAuthImpl
import me.floow.auth.models.GoogleOAuthInfo
import me.floow.domain.auth.AuthenticationManager
import me.floow.domain.auth.GoogleOAuth
import me.floow.login.ui_logic.LoginViewModel
import me.floow.uikit.theme.FlowTheme
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
                clientId = BuildConfig.GOOGLE_CLIENT_ID,
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

        enableEdgeToEdge()

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