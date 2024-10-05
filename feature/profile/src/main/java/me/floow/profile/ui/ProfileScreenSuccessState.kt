package me.floow.profile.ui

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.floow.profile.uilogic.ProfileScreenState
import me.floow.profile.uilogic.ProfileSubscribers
import me.floow.uikit.R
import me.floow.uikit.components.topbar.TitleTopBarWithActionButton
import me.floow.uikit.theme.LocalTypography
import me.floow.uikit.theme.NinehedronShape

@Composable
fun ProfileScreenSuccessState(
	state: ProfileScreenState.Success,
	onProfileEditClick: () -> Unit,
	onAddPostButtonClick: () -> Unit,
	onShareButtonClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	Scaffold(
		topBar = {
			ProfileScreenTopBar(
				username = state.shortUsername,
				onProfileEditClick = onProfileEditClick,
				modifier = Modifier.fillMaxWidth()
			)
		},
		modifier = modifier,
	) { innerPadding ->
		Column(
			modifier = Modifier
				.padding(innerPadding)
		) {
			ProfileSummarySegment(
				profileAvatarUri = state.avatarUri,
				displayName = state.displayName,
				description = state.description,
				subscribers = state.subscribers,
				Modifier.fillMaxWidth(),
			)

			ProfileButtonsSegment(
				onAddPostButtonClick = onAddPostButtonClick,
				onShareButtonClick = onShareButtonClick,
				Modifier.fillMaxWidth(),
			)

			HorizontalDivider()

			ProfileContentSegment(
				Modifier.fillMaxWidth(),
			)
		}
	}
}

@Composable
private fun ProfileSummarySegment(
	profileAvatarUri: Uri,
	displayName: String,
	description: String,
	subscribers: ProfileSubscribers,
	modifier: Modifier = Modifier
) {
	val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })

	HorizontalPager(
		state = pagerState,
		modifier = modifier
			.height(330.dp)
	) { page ->
		Box(
			modifier = modifier
				.background(Color.DarkGray)
				.fillMaxSize()
		) {
			when (page) {
				0 -> {
					AvatarUsernameProfileSummaryPage(
						profileAvatarUri = profileAvatarUri,
						displayName = displayName,
						subscribers = subscribers,
						modifier = Modifier.fillMaxSize()
					)
				}

				1 -> {
					AboutMeProfileSummaryPage(
						description = description,
						modifier = Modifier.fillMaxSize()
					)
				}
			}
		}
	}
}

@Composable
private fun AvatarUsernameProfileSummaryPage(
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

@Composable
private fun AboutMeProfileSummaryPage(description: String, modifier: Modifier = Modifier) {
	Column(
		modifier = modifier
			.padding(horizontal = 24.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = "обо мне",
			style = LocalTypography.current.titleMedium,
			color = Color.White,
		)

		Spacer(Modifier.height(9.dp))

		Text(
			text = description,
			style = LocalTypography.current.bodyMedium,
			color = Color.White,
		)
	}
}

@Composable
private fun ProfileButtonsSegment(
	onAddPostButtonClick: () -> Unit,
	onShareButtonClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier
			.height(75.dp),
		horizontalArrangement = Arrangement
			.spacedBy(78.dp, Alignment.CenterHorizontally),
		verticalAlignment = Alignment.CenterVertically
	) {
		IconButton(onClick = onAddPostButtonClick) {
			Icon(
				painter = painterResource(R.drawable.add_post_outline_icon),
				contentDescription = null,
				modifier = Modifier.size(27.dp)
			)
		}

		VerticalDivider(Modifier.height(18.dp))

		IconButton(onClick = onShareButtonClick) {
			Icon(
				painter = painterResource(R.drawable.share_icon),
				contentDescription = null,
				modifier = Modifier.size(27.dp)
			)
		}
	}
}

@Composable
private fun ProfileContentSegment(modifier: Modifier = Modifier) {
	Box(modifier)
}

@Composable
private fun ProfileScreenTopBar(username: String, onProfileEditClick: () -> Unit, modifier: Modifier = Modifier) {
	TitleTopBarWithActionButton(
		titleText = "@$username",
		onActionButtonClick = onProfileEditClick,
		icon = {
			Icon(
				painter = painterResource(R.drawable.edit_icon),
				contentDescription = null,
			)
		},
		modifier = modifier
	)
}

@Preview
@Composable
fun ProfileScreenSuccessStatePreview() {
	ProfileScreenSuccessState(
		state = ProfileScreenState.Success(
			shortUsername = "demndevel",
			avatarUri = Uri.parse("https://http.cat/images/101.jpg"),
			displayName = "demndevel",
			description = "some description idk. I like eating lorem ipsum. Dolor sit amet. meow!",
			subscribers = ProfileSubscribers(
				firstAvatar = Uri.parse("https://http.cat/images/101.jpg"),
				secondAvatar = Uri.parse("https://http.cat/images/101.jpg"),
				subscribersCount = 1567
			)
		),
		onProfileEditClick = {},
		onAddPostButtonClick = {},
		onShareButtonClick = {},
		modifier = Modifier.fillMaxWidth()
	)
}