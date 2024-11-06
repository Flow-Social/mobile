package me.floow.chatssearch.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.floow.chatssearch.ui.components.SearchUsersScreenTopBar
import me.floow.chatssearch.ui.states.NoSearchInputState
import me.floow.chatssearch.ui.states.LoadingState
import me.floow.chatssearch.ui.states.SearchResultsState
import me.floow.chatssearch.uilogic.SearchUsersScreenUiState

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