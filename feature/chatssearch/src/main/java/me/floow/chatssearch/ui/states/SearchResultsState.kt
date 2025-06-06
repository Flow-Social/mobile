package me.floow.chatssearch.ui.states

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.floow.chatssearch.ui.components.globalSearchUsersList
import me.floow.chatssearch.ui.components.messageResultsList
import me.floow.chatssearch.uilogic.MessageResult
import me.floow.chatssearch.uilogic.SearchUsersScreenUiState
import me.floow.chatssearch.uilogic.UserSearchResult
import me.floow.domain.values.ProfileName
import me.floow.domain.values.ProfileUsername

@Composable
fun SearchResultsState(
	state: SearchUsersScreenUiState.HasResults,
	modifier: Modifier = Modifier
) {
	val context = LocalContext.current
	var isGlobalUsersSearchExpanded by remember { mutableStateOf(false) }

	Column(modifier = modifier) {
		LazyColumn(
			modifier = Modifier.fillMaxSize(),
		) {
			if (state.userResults.isNotEmpty()) {
				globalSearchUsersList(
					isExpanded = isGlobalUsersSearchExpanded,
					onExpandedToggle = {
						isGlobalUsersSearchExpanded = !isGlobalUsersSearchExpanded
					},
					results = state.userResults,
					onClick = { result ->
						Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show()
					}
				)

				item {
					Spacer(Modifier.height(12.dp))

					HorizontalDivider()
				}
			}

			messageResultsList(
				results = state.messageResults,
				onClick = { result ->
					Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show()
				}
			)
		}
	}
}

@Preview
@Composable
private fun SearchResultsStatePreview() {
	SearchResultsState(
		state = SearchUsersScreenUiState.HasResults(
			searchField = "test",
			userResults = listOf(
				UserSearchResult(
					name = ProfileName.create("Demn"),
					username = ProfileUsername.create("demndevel"),
					isOnline = false
				)
			),
			messageResults = listOf(
				MessageResult(
					name = ProfileName.create("Finsi"),
					messageText = "Some example text. Some example text. Some example text. Some example text. Some example text. Some example text. Some example text. "
				),
				MessageResult(
					name = ProfileName.create("Demn"),
					messageText = "Some example text"
				),
				MessageResult(
					name = ProfileName.create("Finsi"),
					messageText = "Some example text. Some example text. Some example text. Some example text. Some example text. Some example text. Some example text. "
				),
				MessageResult(
					name = ProfileName.create("Demn"),
					messageText = "Some example text"
				),
				MessageResult(
					name = ProfileName.create("Finsi"),
					messageText = "Some example text. Some example text. Some example text. Some example text. Some example text. Some example text. Some example text. "
				)
			)
		),
		Modifier.fillMaxSize()
	)
}