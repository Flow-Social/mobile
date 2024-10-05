package me.floow.profile.ui.segments.summary

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.floow.profile.ui.SubscribersLabel
import me.floow.profile.uilogic.ProfileSubscribers
import me.floow.uikit.theme.LocalTypography
import me.floow.uikit.theme.NinehedronShape

@Composable
internal fun AvatarUsernameProfileSummaryPage(
	profileAvatarUri: Uri,
	displayName: String,
	subscribers: ProfileSubscribers,
	modifier: Modifier = Modifier
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
		modifier = modifier,
	) {
		AsyncImage(
			model = profileAvatarUri,
			contentDescription = null,
			contentScale = ContentScale.Fit,
			modifier = Modifier
				.clip(NinehedronShape)
				.border(2.dp, Color.White, NinehedronShape)
				.size(149.dp)
		)

		Spacer(Modifier.height(9.dp))

		Text(
			text = displayName,
			style = LocalTypography.current.titleMedium,
			color = Color.White,
		)

		Spacer(Modifier.height(17.dp))

		SubscribersLabel(
			onClick = {},
			subscribers = subscribers,
			modifier = Modifier
		)
	}
}
