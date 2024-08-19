package com.flowme.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.flowme.api.AuthApiImpl
import com.flowme.auth.models.GoogleOAuthInfo
import com.flowme.domain.auth.models.GoogleOAuthResult
import com.flowme.flow.ui.App
import com.flowme.uikit.theme.FlowTheme
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Make build environments
        val googleOAuthImpl = GoogleOAuthImpl(
            context = this,
            googleOAuthInfo = GoogleOAuthInfo(
                serverClientId = "529714055602-brug0jk1196as3q7mns0l4bna99phrac.apps.googleusercontent.com"
            )
        )

        CoroutineScope(Dispatchers.Default).launch {
            val result = googleOAuthImpl.startSignIn()
            println(result)

            if (result is GoogleOAuthResult.Success) {
                val api = AuthApiImpl()
                val apiResult = api.authorize(result.idToken)

                withContext(Dispatchers.Main) {
                    println(apiResult)
                }
            }
        }

        setContent {
            FlowTheme {
                App(Modifier.fillMaxSize())
            }
        }
    }
}