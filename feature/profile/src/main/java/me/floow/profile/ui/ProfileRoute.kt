package me.floow.profile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import me.floow.profile.uilogic.ProfileScreenState
import me.floow.profile.uilogic.ProfileScreenViewModel

@Composable
fun ProfileRoute(
	viewModel: ProfileScreenViewModel,
	modifier: Modifier = Modifier
) {
	val state: ProfileScreenState by viewModel.state.collectAsState()

	LaunchedEffect(Unit) {
		viewModel.loadData()
	}

	ProfileScreen(
		state = state,
		modifier = modifier
	)
}