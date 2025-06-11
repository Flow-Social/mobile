package me.floow.chatssearch.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.floow.chatssearch.ui.components.SearchUsersScreenTopBar
import me.floow.chatssearch.ui.states.NoSearchInputState
import me.floow.chatssearch.ui.states.LoadingState
import me.floow.chatssearch.ui.states.SearchResultsState
import me.floow.chatssearch.uilogic.MessageResult
import me.floow.chatssearch.uilogic.SearchUsersScreenUiState
import me.floow.chatssearch.uilogic.UserSearchResult
import me.floow.domain.values.ProfileName
import me.floow.domain.values.ProfileUsername

@Composable
internal fun SearchUsersScreen(
	onBackClick: () -> Unit,
	onSearchFieldUpdate: (String) -> Unit,
	state: SearchUsersScreenUiState,
	modifier: Modifier = Modifier
) {
	Scaffold(
		topBar = {
			SearchUsersScreenTopBar(
				onBackClick = onBackClick,
				searchFieldValue = state.searchField,
				onSearchFieldUpdate = onSearchFieldUpdate,
			)
		},
		contentWindowInsets = WindowInsets(0.dp),
		modifier = modifier,
	) { innerPadding ->
		val contentModifier = Modifier
			.fillMaxSize()
			.padding(innerPadding)

		when (state) {
			is SearchUsersScreenUiState.Loading -> {
				LoadingState(
					modifier = contentModifier
				)
			}

			is SearchUsersScreenUiState.NoSearchInput -> {
				NoSearchInputState(
					state = state,
					modifier = contentModifier
				)
			}

			is SearchUsersScreenUiState.HasResults -> {
				SearchResultsState(
					state = state,
					modifier = contentModifier
				)
			}
		}
	}
}

@Preview
@Composable
private fun SearchUsersScreenPreview() {
	SearchUsersScreen(
		onBackClick = { },
		onSearchFieldUpdate = { },
		state = SearchUsersScreenUiState.HasResults(
			searchField = "test",
			userResults = listOf(
				UserSearchResult(
					name = ProfileName.create("Demn"),
					username = ProfileUsername.create("demndevel"),
					isOnline = false,
					avatarUrl = "https://http.cat/images/101.jpg"
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
				),
				MessageResult(
					name = ProfileName.create("Demn"),
					messageText = "Some example text"
				),
			)
		),
		modifier = Modifier.fillMaxSize()
	)
}
