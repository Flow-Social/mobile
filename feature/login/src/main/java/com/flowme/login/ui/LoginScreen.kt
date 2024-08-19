package com.flowme.login.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flowme.login.ui.components.GoogleLoginButton
import com.flowme.login.ui.components.TermsAndPolicyText
import com.flowme.uikit.R
import com.flowme.uikit.theme.FlowTheme

@Composable
internal fun LoginScreen(
    onLoginWithGoogle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))

        Icon(
            painterResource(R.drawable.flow_logo),
            null,
            tint = Color.Unspecified
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.social_media_flow_exclamation),
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.flow_onboarding_explanation),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .width(326.dp)
        )

        Spacer(Modifier.height(24.dp))

        GoogleLoginButton(onLoginWithGoogle, Modifier)

        Spacer(Modifier.weight(1f))

        TermsAndPolicyText(
            onTermsClick = {
                val url = "https://en.wikipedia.org/wiki/Terms_of_service"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            },
            onPrivacyClick = {
                val url = "https://en.wikipedia.org/wiki/Privacy_policy"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            },
            Modifier.padding(horizontal = 10.dp)
        )

        Spacer(Modifier.height(24.dp))
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    FlowTheme {
        Surface {
            LoginScreen({}, Modifier.fillMaxSize())
        }
    }
}