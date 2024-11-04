package me.floow.profile.ui.profile

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.floow.profile.ui.profile.segments.buttons.ProfileButtonsSegment
import me.floow.profile.ui.profile.segments.content.ProfileContentSegment
import me.floow.profile.ui.profile.segments.summary.ProfileSummarySegment
import me.floow.profile.uilogic.profile.ProfileScreenState
import me.floow.profile.uilogic.profile.ProfileSubscribers

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
				modifier = Modifier.fillMaxWidth(),
			)

			HorizontalDivider()

			ProfileContentSegment(
				Modifier.fillMaxWidth(),
			)
		}
	}
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