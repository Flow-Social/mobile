package me.floow.chatssearch.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.floow.chatssearch.uilogic.RecentUser
import me.floow.uikit.theme.LocalTypography

@Composable
fun RecentUsersList(
	recentUsers: List<RecentUser>,
	modifier: Modifier = Modifier
) {
	LazyRow(
		modifier = modifier,
		horizontalArrangement = Arrangement.spacedBy(12.dp)
	) {
		item {
			Spacer(Modifier.width(2.dp))
		}

		items(recentUsers) { recentUser ->
			RecentUsersListItem(
				recentUser,
				Modifier.fillMaxWidth()
			)
		}

		item {
			Spacer(Modifier.width(2.dp))
		}
	}
}

@Composable
fun RecentUsersListItem(
	recentUser: RecentUser,
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Box(
			modifier = Modifier
				.size(64.dp)
				.clip(CircleShape)
				.background(Color.LightGray)
		)

		Spacer(Modifier.height(8.dp))

		Text(
			text = recentUser.name.value,
			style = LocalTypography.current.bodyMedium
		)
	}
}