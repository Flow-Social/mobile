package com.demn.usersearch.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.demn.usersearch.uilogic.UserSearchResult
import me.floow.uikit.theme.LocalTypography
import me.floow.usersearch.R

@Composable
fun GlobalSearchUsersList(
	results: List<UserSearchResult>,
	onClick: (UserSearchResult) -> Unit,
	modifier: Modifier = Modifier
) {
	var isExpanded by remember { mutableStateOf(false) }

	Column(modifier) {
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
							isExpanded = !isExpanded
						}
				)
			}
		}

		Spacer(Modifier.height(12.dp))

		LazyColumn(
			verticalArrangement = Arrangement.spacedBy(8.dp),
			modifier = Modifier,
		) {
			val croppedResults = if (!isExpanded) results.take(3) else results

			items(croppedResults) { result ->
				UserGlobalSearchResult(
					result,
					onClick = onClick,
					modifier = Modifier.fillMaxWidth()
				)
			}
		}
	}
}