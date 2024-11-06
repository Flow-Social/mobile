package com.demn.usersearch.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.demn.usersearch.uilogic.SearchUsersScreenViewModel

@Composable
fun SearchUsersRoute(
	onBackClick: () -> Unit,
	onUserPick: () -> Unit,
	vm: SearchUsersScreenViewModel,
	modifier: Modifier = Modifier
) {
	val state by vm.state.collectAsState()

	LaunchedEffect(Unit) {
		vm.loadInitialData()
	}

	SearchUsersScreen(
		onBackClick = onBackClick,
		onSearchFieldUpdate = vm::updateSearchField,
		state = state,
		modifier = modifier
	)
}