package com.demn.usersearch.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.demn.usersearch.uilogic.SearchUsersScreenViewModel

@Composable
fun SearchUsersRoute(
	onUserPick: () -> Unit,
	vm: SearchUsersScreenViewModel,
	modifier: Modifier = Modifier
) {
	val state by vm.state.collectAsState()

	SearchUsersScreen(
		state = state,
		modifier = modifier
	)
}