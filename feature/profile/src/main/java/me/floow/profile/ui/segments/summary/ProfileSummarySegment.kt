package me.floow.profile.ui.segments.summary

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.floow.profile.uilogic.ProfileSubscribers

@Composable
fun ProfileSummarySegment(
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