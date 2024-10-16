package me.floow.profile.ui.edit

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import me.floow.profile.uilogic.edit.EditProfileViewModel

@Composable
fun EditProfileRoute(
	onBackClick: () -> Unit,
	onDoneClick: () -> Unit,
	vm: EditProfileViewModel,
	modifier: Modifier = Modifier
) {
	val state by vm.state.collectAsState()
	val context = LocalContext.current

	LaunchedEffect(Unit) {
		vm.loadData()
	}

	EditProfileScreen(
		state = state,
		onBackClick = onBackClick,
		onDoneClick = {
			vm.updateProfile(
				onSuccess = onDoneClick,
				onFailure = {
					val toast = Toast.makeText(context, "Failure", Toast.LENGTH_SHORT)
					toast.show()
				}
			)
		},
		onAvatarPickerClick = { TODO() },
		onNameChange = vm::updateName,
		onUsernameChange = vm::updateUsername,
		onBiographyChange = vm::updateBiography,
		modifier = modifier,
	)
}