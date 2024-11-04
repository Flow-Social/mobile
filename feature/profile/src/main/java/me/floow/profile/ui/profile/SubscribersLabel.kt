package me.floow.profile.ui.profile

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.floow.profile.uilogic.profile.ProfileSubscribers
import me.floow.uikit.R
import me.floow.uikit.theme.LocalTypography
import me.floow.uikit.util.toShortenedString

@Composable
fun SubscribersLabel(
	onClick: () -> Unit,
	subscribers: ProfileSubscribers,
	modifier: Modifier = Modifier
) {
	val currentLocale = Locale.current

	Row(
		modifier.width(IntrinsicSize.Min),
	) {
		when (subscribers.subscribersCount) {
			0 -> {
				SubscribersLabel(onClick = onClick, stringResource(R.string.no_subscribers))
			}

			1 -> {
				SubscribersLabel(onClick = onClick, stringResource(R.string.one_subscriber))
			}

			else -> {
				Row(
					modifier = Modifier,
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.spacedBy((-10).dp),
				) {
					Avatar(
						avatarUri = subscribers.firstAvatar,
						modifier = Modifier
							.size(30.dp)
					)

					Avatar(
						avatarUri = subscribers.secondAvatar,
						modifier = Modifier
							.size(30.dp)
					)

					SubscribersLabel(
						onClick = onClick,
						text = "${subscribers.subscribersCount.toShortenedString(currentLocale.platformLocale)} ${
							stringResource(
								R.string.subscribers_label
							)
						}"
					)
				}
			}
		}
	}
}

@Composable
private fun SubscribersLabel(onClick: () -> Unit, text: String, modifier: Modifier = Modifier) {
	Text(
		text = text,
		style = LocalTypography.current.labelMedium,
		color = Color.White,
		textAlign = TextAlign.Center,
		modifier = modifier
			.clip(RoundedCornerShape(16.dp))
			.background(color = Color.Black)
			.border(2.dp, Color.White, RoundedCornerShape(16.dp))
			.height(30.dp)
			.width(128.dp)
			.wrapContentHeight(align = Alignment.CenterVertically)
			.clickable(onClick = onClick)
	)
}

@Composable
private fun Avatar(
	avatarUri: Uri?,
	modifier: Modifier = Modifier
) {
	AsyncImage(
		model = avatarUri,
		contentDescription = null,
		modifier = modifier
			.size(27.dp)
			.clip(CircleShape)
			.border(2.dp, Color.White, CircleShape)
			.background(Color.LightGray)
	)
}

@Preview(showBackground = false)
@Composable
private fun SubscribersLabelPreview_NoSubs(modifier: Modifier = Modifier) {
	Box(
		Modifier
			.size(300.dp)
			.background(Color.DarkGray), contentAlignment = Alignment.Center
	) {
		SubscribersLabel(
			subscribers = ProfileSubscribers(
				subscribersCount = 0
			),
			onClick = {},
			modifier = Modifier.padding(24.dp)
		)
	}
}

@Preview(showBackground = false)
@Composable
private fun SubscribersLabelPreview_OneSub(modifier: Modifier = Modifier) {
	Box(
		Modifier
			.size(300.dp)
			.background(Color.DarkGray), contentAlignment = Alignment.Center
	) {
		SubscribersLabel(
			subscribers = ProfileSubscribers(
				subscribersCount = 1
			),
			onClick = {},
			modifier = Modifier.padding(24.dp)
		)
	}
}

@Preview(showBackground = false)
@Composable
private fun SubscribersLabelPreview_MoreThan2(modifier: Modifier = Modifier) {
	Box(
		Modifier
			.size(300.dp)
			.background(Color.DarkGray), contentAlignment = Alignment.Center
	) {
		SubscribersLabel(
			subscribers = ProfileSubscribers(
				firstAvatar = Uri.parse("https://http.cat/images/101.jpg"),
				secondAvatar = Uri.parse("https://http.cat/images/500.jpg"),
				subscribersCount = 4
			),
			onClick = {},
			modifier = Modifier.padding(24.dp)
		)
	}
}

@Preview(showBackground = false)
@Composable
private fun SubscribersLabelPreview_VeryMuchSubs(modifier: Modifier = Modifier) {
	Box(
		Modifier
			.size(300.dp)
			.background(Color.DarkGray), contentAlignment = Alignment.Center
	) {
		SubscribersLabel(
			subscribers = ProfileSubscribers(
				firstAvatar = null,
				secondAvatar = null,
				subscribersCount = 1_700_000
			),
			onClick = {},
			modifier = Modifier.padding(24.dp)
		)
	}
}