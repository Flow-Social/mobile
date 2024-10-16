package me.floow.login.ui.createprofile

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import me.floow.login.uilogic.CreateProfileViewModel

@Composable
fun CreateProfileRoute(
	onDone: () -> Unit,
	vm: CreateProfileViewModel,
	modifier: Modifier = Modifier
) {
	val state by vm.state.collectAsState()
	val context = LocalContext.current

	CreateProfileScreen(
		state = state,
		onAvatarPickerClick = { TODO() },
		onNameChange = vm::updateName,
		onUsernameChange = vm::updateUsername,
		onBiographyChange = vm::updateBio,
		onDoneClick = {
			vm.createProfile(
				onSuccess = onDone,
				onFailure = {
					val toast = Toast.makeText(context, "Failure", Toast.LENGTH_SHORT)
					toast.show()
				}
			)
		},
		modifier = modifier
	)
}