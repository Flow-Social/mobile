package com.demn.usersearch.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.demn.usersearch.ui.components.SearchUsersScreenTopBar
import com.demn.usersearch.ui.states.NoSearchInputState
import com.demn.usersearch.ui.states.LoadingState
import com.demn.usersearch.ui.states.SearchResultsState
import com.demn.usersearch.uilogic.SearchUsersScreenUiState

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