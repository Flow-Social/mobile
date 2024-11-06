package com.demn.usersearch.ui.states

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.demn.usersearch.ui.components.RecentUsersList
import com.demn.usersearch.uilogic.SearchUsersScreenUiState
import me.floow.uikit.theme.LocalTypography

@Composable
fun NoSearchInputState(
	state: SearchUsersScreenUiState.NoSearchInput,
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier
	) {
		if (state.recentUsers.isNotEmpty()) {
			Text(
				text = "Recent searches",
				style = LocalTypography.current.bodyMedium,
				fontWeight = FontWeight.Bold,
				modifier = Modifier
					.padding(horizontal = 14.dp, vertical = 12.dp)
			)

			RecentUsersList(
				recentUsers = state.recentUsers,
				modifier = Modifier
			)
		}
	}
}