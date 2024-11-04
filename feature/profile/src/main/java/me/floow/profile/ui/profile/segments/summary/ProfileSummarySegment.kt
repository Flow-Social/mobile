package me.floow.profile.ui.profile.segments.summary

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.floow.profile.uilogic.profile.ProfileSubscribers

@Composable
fun ProfileSummarySegment(
	profileAvatarUri: Uri?,
	displayName: String?,
	description: String?,
	subscribers: ProfileSubscribers,
	modifier: Modifier = Modifier
) {
	val pageCount = 2
	val pagerState = rememberPagerState(initialPage = 0, pageCount = { pageCount })

	Box(modifier) {
		HorizontalPager(
			state = pagerState,
			modifier = Modifier
				.height(330.dp)
		) { page ->
			Box(
				modifier = Modifier
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

		PagerIndicator(
			pageCount = pageCount,
			currentPage = pagerState.currentPage,
			modifier = Modifier
				.align(Alignment.BottomCenter)
				.padding(bottom = 22.dp)
		)
	}
}

@Composable
private fun PagerIndicator(
	currentPage: Int,
	pageCount: Int,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier,
		horizontalArrangement = Arrangement.spacedBy(4.dp)
	) {
		for (page in 0..<pageCount) {
			Box(
				modifier = Modifier
					.clip(CircleShape)
					.background(if (currentPage == page) Color.White else Color.LightGray)
					.size(6.dp)
			)
		}
	}
}