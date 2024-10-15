package me.floow.login.ui.login.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.floow.uikit.util.ComponentPreviewBox
import me.flowme.login.R

@Composable
internal fun GoogleLoginButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledTonalButton(
        onClick = onClick,
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
        modifier = modifier
    ) {
        Icon(
            painterResource(R.drawable.google_login_icon),
            null,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(18.dp)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = stringResource(R.string.google_login_btn_text),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Preview
@Composable
private fun GoogleLoginButtonPreview() {
    ComponentPreviewBox(Modifier.size(200.dp)) {
        GoogleLoginButton({}, Modifier)
    }
}