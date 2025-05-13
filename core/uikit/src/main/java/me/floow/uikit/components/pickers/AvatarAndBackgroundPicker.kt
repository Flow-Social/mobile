package me.floow.uikit.components.pickers

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import me.floow.uikit.R
import me.floow.uikit.theme.ElevanagonShape
import me.floow.uikit.theme.LocalTypography

@Composable
fun AvatarAndBackgroundPicker(
	backgroundImagePainter: Painter? = null,
	avatarUri: Uri? = null,
	onAvatarChanged: (Uri) -> Unit,
	onBackgroundPickerClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	Box(
		contentAlignment = Alignment.Center,
		modifier = modifier
			.fillMaxWidth()
			.height(160.dp)
			.clip(RoundedCornerShape(16.dp))
			.setBackgroundBoxImage(backgroundImagePainter)
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

		val pickMediaLauncher =
			rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
				if (uri != null) onAvatarChanged(uri)
			}

		Box(
			contentAlignment = Alignment.Center,
			modifier = Modifier
				.size(120.dp)
				.clip(ElevanagonShape)
				.setAvatarBoxImage(avatarUri)
				.clickable {
					pickMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
				}
		) {
			if (avatarUri == null) Icon(
				painter = painterResource(R.drawable.photo_icon),
				contentDescription = null,
				tint = Color.White,
			)
		}
	}
}

@Composable
private fun Modifier.setAvatarBoxImage(avatarUri: Uri?): Modifier {
	return if (avatarUri == null) {
		this.then(
			Modifier
				.background(Color.LightGray)
		)
	} else {
		this.then(
			Modifier
				.paint(
					painter = rememberAsyncImagePainter(avatarUri.toString()),
					contentScale = ContentScale.FillBounds
				)
		)
	}
}

@Composable
private fun Modifier.setBackgroundBoxImage(backgroundImagePainter: Painter?): Modifier {
	return if (backgroundImagePainter == null) {
		this.then(
			Modifier
				.background(MaterialTheme.colorScheme.primaryContainer)
		)
	} else {
		Modifier
			.paint(
				painter = backgroundImagePainter,
				contentScale = ContentScale.FillBounds
			)
	}
}

@Preview
@Composable
fun AvatarAndBackgroundPickerPreview() {
	AvatarAndBackgroundPicker(null, null, {}, {}, Modifier.fillMaxWidth())
}