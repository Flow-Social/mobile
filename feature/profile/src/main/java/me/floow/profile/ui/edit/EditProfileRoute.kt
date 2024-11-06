package me.floow.profile.ui.edit

import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.floow.profile.uilogic.edit.EditProfileViewModel
import me.floow.uikit.util.SetNavigationBarColor

data class EditProfileRouteInitialData(
	val name: String,
	val username: String,
	val description: String,
)

@Composable
fun EditProfileRoute(
	initialData: EditProfileRouteInitialData,
	onBackClick: () -> Unit,
	onDoneClick: () -> Unit,
	vm: EditProfileViewModel,
	modifier: Modifier = Modifier
) {
	val state by vm.state.collectAsState()
	val context = LocalContext.current
	val hapticFeedback = LocalHapticFeedback.current
	val lifecycle = LocalLifecycleOwner.current.lifecycle

	LaunchedEffect(Unit) {
		vm.setInitialData(initialData)

		lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
			launch {
				vm.hapticFeedbackFlow.collectLatest {
					hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
				}
			}
		}
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
		onAvatarPickerClick = {
			Toast.makeText(context, "Фича ещё разрабатывается…", Toast.LENGTH_SHORT).show()
		},
		onNameChange = vm::updateName,
		onUsernameChange = vm::updateUsername,
		onBiographyChange = vm::updateBiography,
		modifier = modifier,
	)

	SetNavigationBarColor(
		MaterialTheme.colorScheme.background
	)
}