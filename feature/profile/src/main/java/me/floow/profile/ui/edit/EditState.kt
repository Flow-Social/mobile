package me.floow.profile.ui.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.floow.profile.R
import me.floow.profile.uilogic.edit.EditProfileState
import me.floow.uikit.components.input.TextFieldWithAdditionalText
import me.floow.uikit.components.pickers.AvatarAndBackgroundPicker
import me.floow.uikit.theme.LocalTypography
import me.floow.uikit.util.state.ValidatedField

@Composable
internal fun EditState(
	onAvatarPickerClick: () -> Unit,
	state: EditProfileState.Edit,
	onNameChange: (String) -> Unit,
	onUsernameChange: (String) -> Unit,
	onBiographyChange: (String) -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(14.dp)
	) {
		AvatarAndBackgroundPicker(
			onAvatarPickerClick,
			{},
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(Modifier.height(24.dp))

		Text(
			text = stringResource(R.string.information).uppercase(),
			style = LocalTypography.current.labelMedium,
			color = MaterialTheme.colorScheme.secondary,
		)

		Spacer(Modifier.height(8.dp))

		TextFieldWithAdditionalText(
			additionalText = "",
			title = stringResource(R.string.name),
			value = state.name.value,
			isError = state.name is ValidatedField.Invalid,
			placeholder = stringResource(R.string.your_name),
			onValueChange = onNameChange,
			modifier = Modifier
				.fillMaxWidth()
		)

		Spacer(Modifier.height(8.dp))

		TextFieldWithAdditionalText(
			additionalText = stringResource(R.string.username_additional_text),
			title = stringResource(R.string.user_id),
			value = state.username.value,
			isError = state.username is ValidatedField.Invalid,
			placeholder = stringResource(R.string.username),
			onValueChange = onUsernameChange,
			supportingText = stringResource(me.floow.uikit.R.string.username_field_supporting_text),
			modifier = Modifier
				.fillMaxWidth()
		)

		Spacer(Modifier.height(8.dp))

		TextFieldWithAdditionalText(
			additionalText = "",
			title = stringResource(R.string.about),
			value = state.bio.value,
			isError = state.bio is ValidatedField.Invalid,
			placeholder = stringResource(R.string.write_something_about_you),
			minLines = 3,
			maxLines = 10,
			singleLine = false,
			onValueChange = onBiographyChange,
			modifier = Modifier
				.fillMaxWidth()
		)
	}
}