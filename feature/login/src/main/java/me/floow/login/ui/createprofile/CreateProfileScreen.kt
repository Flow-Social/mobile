package me.floow.login.ui.createprofile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import me.floow.login.ui.createprofile.components.EditState
import me.floow.login.ui.createprofile.components.UploadingState
import me.floow.login.uilogic.CreateProfileState
import me.floow.uikit.components.topbar.TitleTopBarWithActionButton
import me.flowme.login.R

@Composable
fun CreateProfileScreen(
	state: CreateProfileState,
	onAvatarPickerClick: () -> Unit = {},
	onNameChange: (String) -> Unit = {},
	onUsernameChange: (String) -> Unit = {},
	onBiographyChange: (String) -> Unit = {},
	modifier: Modifier = Modifier,
	onDoneClick: () -> Unit = {}
) {
	Scaffold(
		topBar = {
			CreateProfileScreenTopBar(onDoneClick = onDoneClick)
		},
		modifier = modifier
	) { innerPadding ->
		Box(
			modifier = Modifier
				.padding(innerPadding)
				.fillMaxSize()
		) {
			when (state) {
				is CreateProfileState.Uploading -> {
					UploadingState(
						modifier = Modifier
							.fillMaxSize()
					)
				}

				is CreateProfileState.Edit -> {
					EditState(
						state = state,
						onAvatarPickerClick = onAvatarPickerClick,
						onNameChange = onNameChange,
						onBiographyChange = onBiographyChange,
						onUsernameChange = onUsernameChange,
						modifier = Modifier
							.fillMaxSize()
					)
				}
			}
		}
	}
}

@Composable
fun CreateProfileScreenTopBar(onDoneClick: () -> Unit) {
	TitleTopBarWithActionButton(
		titleText = stringResource(R.string.create_profile),
		onActionButtonClick = onDoneClick,
		icon = {
			Icon(
				painter = painterResource(me.floow.uikit.R.drawable.done_icon),
				contentDescription = null
			)
		},
	)
}

@Preview
@Composable
fun CreateProfileScreenPreview() {
	CreateProfileScreen(
		state = CreateProfileState.Edit(),
		onAvatarPickerClick = {},
		modifier = Modifier.fillMaxSize()
	)
}