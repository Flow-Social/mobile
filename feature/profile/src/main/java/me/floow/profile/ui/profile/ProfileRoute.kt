package me.floow.profile.ui.profile

import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import me.floow.profile.uilogic.profile.ProfileScreenState
import me.floow.profile.uilogic.profile.ProfileScreenViewModel
import me.floow.uikit.util.SetNavigationBarColor

@Composable
fun ProfileRoute(
	goToProfileEditScreen: (name: String, username: String, description: String) -> Unit,
	goToAddPostScreen: () -> Unit,
	shareProfile: (url: String) -> Unit,
	modifier: Modifier = Modifier,
	viewModel: ProfileScreenViewModel
) {
	val state: ProfileScreenState by viewModel.state.collectAsState()

	LaunchedEffect(Unit) {
		viewModel.loadData()
	}

	ProfileScreen(
		onProfileEditClick = {
			if (state is ProfileScreenState.Success) {
				goToProfileEditScreen(
					(state as ProfileScreenState.Success).displayName ?: "",
					(state as ProfileScreenState.Success).shortUsername ?: "",
					(state as ProfileScreenState.Success).description ?: "",
				)
			}
		},
		onAddPostButtonClick = goToAddPostScreen,
		onShareButtonClick = {
			if (state is ProfileScreenState.Success) {
				// TODO: use global constant for url
				shareProfile("https://floow.me/" + (state as ProfileScreenState.Success).shortUsername)
			}
		},
		modifier = modifier,
		state = state
	)

	SetNavigationBarColor(
		NavigationBarDefaults.containerColor
	)
}