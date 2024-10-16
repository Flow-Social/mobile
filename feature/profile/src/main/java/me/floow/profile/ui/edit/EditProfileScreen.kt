package me.floow.profile.ui.edit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.floow.profile.R
import me.floow.profile.uilogic.edit.EditProfileState
import me.floow.uikit.components.topbar.TitleTopBarWithActionButtonWithNavBack

@Composable
fun EditProfileScreen(
	state: EditProfileState,
	onBackClick: () -> Unit,
	onDoneClick: () -> Unit,
	onAvatarPickerClick: () -> Unit,
	onNameChange: (String) -> Unit,
	onUsernameChange: (String) -> Unit,
	onBiographyChange: (String) -> Unit,
	modifier: Modifier = Modifier
) {
	Scaffold(
		topBar = {
			EditProfileScreenTopBar(
				onBackClick = onBackClick,
				onDoneClick = onDoneClick
			)
		},
		modifier = modifier
	) { innerPadding ->
		Box(
			modifier = Modifier
				.padding(innerPadding)
				.fillMaxSize()
		) {
			when (state) {
				is EditProfileState.Uploading -> {
					CircularProgressIndicator(
						Modifier
							.size(32.dp)
							.align(Alignment.Center)
					)
				}

				is EditProfileState.Edit -> {
					EditState(
						onAvatarPickerClick,
						state,
						onNameChange,
						onUsernameChange,
						onBiographyChange
					)
				}
			}
		}
	}
}

@Composable
fun EditProfileScreenTopBar(
	onBackClick: () -> Unit,
	onDoneClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	TitleTopBarWithActionButtonWithNavBack(
		titleText = stringResource(R.string.edit),
		onBackClick = onBackClick,
		onActionButtonClick = onDoneClick,
		icon = {
			Icon(
				painter = painterResource(me.floow.uikit.R.drawable.done_icon),
				null
			)
		},
		modifier = modifier
	)
}