package me.floow.uikit.components.pickers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.floow.uikit.R
import me.floow.uikit.theme.ElevanagonShape
import me.floow.uikit.theme.LocalTypography

@Composable
fun AvatarAndBackgroundPicker(
	onAvatarPickerClick: () -> Unit,
	onBackgroundPickerClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	Box(
		contentAlignment = Alignment.Center,
		modifier = modifier
			.fillMaxWidth()
			.height(160.dp)
			.clip(RoundedCornerShape(16.dp))
			.background(MaterialTheme.colorScheme.secondaryContainer)
			.clickable {
				onBackgroundPickerClick()
			},
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.align(Alignment.TopEnd)
				.padding(14.dp)
		) {
			Text(
				text = "изменить фон",
				style = LocalTypography.current.labelMedium,
				color = Color.White
			)

			Spacer(Modifier.width(8.dp))

			Icon(
				painter = painterResource(R.drawable.profile_background_image_icon),
				tint = Color.White,
				contentDescription = null
			)
		}

		Image(
			painter = painterResource(me.floow.uikit.R.drawable.cute_girl),
			contentDescription = null,
			modifier = Modifier
				.size(120.dp)
				.clip(ElevanagonShape)
				.clickable {
					onAvatarPickerClick()
				}
		)

		Icon(
			painter = painterResource(me.floow.uikit.R.drawable.photo_icon),
			contentDescription = null,
			tint = Color.White,
		)
	}
}

@Preview
@Composable
fun AvatarAndBackgroundPickerPreview() {
	AvatarAndBackgroundPicker({}, {}, Modifier.fillMaxWidth())
}