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
	goToProfileEditScreen: () -> Unit,
	goToAddPostScreen: () -> Unit,
	shareProfile: () -> Unit,
	modifier: Modifier = Modifier,
	viewModel: ProfileScreenViewModel
) {
	val state: ProfileScreenState by viewModel.state.collectAsState()

	LaunchedEffect(Unit) {
		viewModel.loadData()
	}

	ProfileScreen(
		onProfileEditClick = goToProfileEditScreen,
		onAddPostButtonClick = goToAddPostScreen,
		onShareButtonClick = shareProfile,
		modifier = modifier,
		state = state
	)

	SetNavigationBarColor(
		NavigationBarDefaults.containerColor
	)
}