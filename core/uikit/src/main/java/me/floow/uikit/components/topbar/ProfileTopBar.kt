package me.floow.uikit.components.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.floow.uikit.components.buttons.WideOutlinedIconButton
import me.floow.uikit.theme.ElevanagonShape
import me.floow.uikit.theme.LocalTypography
import me.floow.uikit.util.ComponentPreviewBox
import me.floow.uikit.R

@Composable
fun ProfileTopBar(
    profileUsername: String,
    profileAvatar: @Composable (Modifier) -> Unit,
    onEditProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            profileAvatar(Modifier.size(50.dp))

            Spacer(Modifier.width(8.dp))

            Column {
                Text(
                    text = profileUsername,
                    style = LocalTypography.current.titleMedium
                )

                Text(
                    text = stringResource(R.string.to_the_profile),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = LocalTypography.current.bodyMedium
                )
            }

            Spacer(Modifier.weight(1f))

            WideOutlinedIconButton(
                onClick = onEditProfileClick,
                modifier = Modifier
            ) {
                Icon(
                    painterResource(R.drawable.edit_icon),
                    null
                )
            }
        }

        HorizontalDivider()
    }
}

@Preview
@Composable
private fun ProfileTopBarPreview() {
    ComponentPreviewBox(Modifier.fillMaxSize()) {
        ProfileTopBar(
            profileUsername = "__Alin04k@__",
            profileAvatar = { modifier ->
                Image(
                    painterResource(R.drawable.cute_girl),
                    null,
                    modifier
                        .clip(ElevanagonShape),
                )
            },
            onEditProfileClick = {},
            modifier = Modifier
                .fillMaxSize()
        )
    }
}