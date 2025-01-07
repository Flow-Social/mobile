package me.floow.login.ui.createprofile

import android.widget.Toast
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
import me.floow.login.uilogic.CreateProfileViewModel

@Composable
fun CreateProfileRoute(
	onDone: () -> Unit,
	vm: CreateProfileViewModel,
	modifier: Modifier = Modifier
) {
	val state by vm.state.collectAsState()
	val context = LocalContext.current
	val hapticFeedback = LocalHapticFeedback.current
	val lifecycle = LocalLifecycleOwner.current.lifecycle

	LaunchedEffect(Unit) {
		lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
			launch {
				vm.hapticFeedbackFlow.collectLatest {
					hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
				}
			}
		}
	}

	CreateProfileScreen(
		state = state,
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
		onAvatarChanged = {
			vm.uploadAvatar(
				newAvatarUri = it,
				onFailure = {
					Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show()
				},
				context = context,
			)

		},
		modifier = modifier
	)
}