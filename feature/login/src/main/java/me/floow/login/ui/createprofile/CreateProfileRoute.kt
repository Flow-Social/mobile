package me.floow.login.ui.createprofile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import me.floow.login.uilogic.CreateProfileViewModel

@Composable
fun CreateProfileRoute(
	vm: CreateProfileViewModel,
	modifier: Modifier = Modifier
) {
	val state by vm.state.collectAsState()

	CreateProfileScreen(
		state = state,
		onAvatarPickerClick = { TODO() },
		onNameChange = vm::updateName,
		onUsernameChange = vm::updateUsername,
		onBiographyChange = vm::updateBio,
		onDoneClick = { TODO() },
		modifier = modifier
	)
}