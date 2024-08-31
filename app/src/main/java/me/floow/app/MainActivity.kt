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
import me.floow.app.di.*
import me.floow.app.navigation.NavigationItem
import me.floow.app.ui.App
import me.floow.domain.auth.AuthenticationManager
import me.floow.login.di.loginModule
import me.floow.uikit.theme.FlowTheme
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.*

class MainActivity : ComponentActivity() {
    private lateinit var coroutineScope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)

            modules(
                appModule,
                apiModule,
                authModule,
                databaseModule,
                dataModule,
                domainModule,
                mockModule,
                loginModule
            )
        }

        coroutineScope = CoroutineScope(CoroutineName("MainActivity"))
        val authenticationManager: AuthenticationManager = getKoin().get()

        enableEdgeToEdge()

        val startDestination = when (authenticationManager.isSignedIn()) {
            false -> NavigationItem.Auth.route
            true -> NavigationItem.Main.route
        }

        setContent {
            FlowTheme {
                App(
                    startDestination = startDestination,
                    Modifier.fillMaxSize()
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        val authenticationManager: AuthenticationManager = getKoin().get()

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