package me.floow.chatssearch.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import me.floow.chatssearch.uilogic.UserSearchResult
import me.floow.uikit.theme.LocalTypography
import me.floow.chatssearch.R

fun LazyListScope.globalSearchUsersList(
	isExpanded: Boolean,
	onExpandedToggle: () -> Unit,
	results: List<UserSearchResult>,
	onClick: (UserSearchResult) -> Unit,
) {
	item {
		Spacer(Modifier.height(12.dp))

		Row(
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 14.dp),
		) {
			Text(
				text = stringResource(R.string.global_search),
				style = LocalTypography.current.bodyMedium,
				fontWeight = FontWeight.Bold
			)

			if (results.size > 3) {
				Text(
					text = stringResource(if (isExpanded) R.string.show_less else R.string.show_more),
					style = LocalTypography.current.bodyMedium,
					modifier = Modifier
						.clickable {
							onExpandedToggle()
						}
				)
			}
		}
		
		Spacer(Modifier.height(12.dp))
	}

	val croppedResults = if (!isExpanded) results.take(3) else results

	items(croppedResults) { result ->
		UserGlobalSearchResult(
			result,
			onClick = onClick,
			modifier = Modifier.fillMaxWidth()
		)
	}
}