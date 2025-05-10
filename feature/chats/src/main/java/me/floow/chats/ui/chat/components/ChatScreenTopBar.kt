package me.floow.chats.ui.chat.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import me.floow.uikit.R
import me.floow.uikit.components.buttons.WideOutlinedIconButton
import me.floow.uikit.theme.ElevanagonShape
import me.floow.uikit.theme.LocalTypography
import me.floow.uikit.util.ComponentPreviewBox

@Composable
fun ChatScreenTopBar(
	profileName: String,
	isOnline: Boolean,
	profileAvatar: @Composable (Modifier) -> Unit,
	onProfileClick: () -> Unit,
	onDropdownClick: () -> Unit,
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
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.clickable { onProfileClick() }
					.weight(1f),
			) {
				profileAvatar(Modifier.size(50.dp))

				Spacer(Modifier.width(8.dp))

				Column() {
					Text(
						text = profileName,
						style = LocalTypography.current.titleLarge
					)

					Text(
						text = stringResource(if (isOnline) R.string.online else R.string.offline),
						color = MaterialTheme.colorScheme.onSurfaceVariant,
						style = LocalTypography.current.bodyMedium
					)
				}

				Spacer(Modifier.width(16.dp))
			}

			WideOutlinedIconButton(
				onClick = onDropdownClick,
				modifier = Modifier
			) {
				Icon(
					painterResource(R.drawable.dropdown_icon),
					null
				)
			}
		}

		HorizontalDivider()
	}
}

@Preview
@Composable
private fun ChatScreenTopBarPreview() {
	ComponentPreviewBox(Modifier.fillMaxSize()) {
		ChatScreenTopBar(
			profileName = "Alina",
			isOnline = false,
			profileAvatar = { modifier ->
				Image(
					painterResource(R.drawable.cute_girl),
					null,
					modifier
						.clip(ElevanagonShape),
				)
			},
			onDropdownClick = {},
			onProfileClick = {},
			modifier = Modifier
				.fillMaxSize()
		)
	}
}