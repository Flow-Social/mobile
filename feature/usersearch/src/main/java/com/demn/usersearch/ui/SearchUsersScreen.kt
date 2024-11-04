package com.demn.usersearch.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.demn.usersearch.uilogic.SearchUsersScreenUiState

@Composable
internal fun SearchUsersScreen(
	state: SearchUsersScreenUiState,
	modifier: Modifier = Modifier
) {
	Scaffold(
		topBar = {

		},
		modifier = modifier,
	) { innerPadding ->
		Box(modifier = Modifier.padding(innerPadding)) {

		}
	}
}