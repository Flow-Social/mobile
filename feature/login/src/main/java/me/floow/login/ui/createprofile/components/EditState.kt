package me.floow.login.ui.createprofile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.floow.login.uilogic.CreateProfileState
import me.floow.uikit.components.input.TextFieldWithAdditionalText
import me.floow.uikit.components.pickers.AvatarAndBackgroundPicker
import me.floow.uikit.theme.ElevanagonShape
import me.floow.uikit.theme.LocalTypography
import me.floow.uikit.util.state.ValidatedField
import me.flowme.login.R

@Composable
fun EditState(
	state: CreateProfileState.Edit,
	onAvatarPickerClick: () -> Unit = {},
	onNameChange: (String) -> Unit = {},
	onUsernameChange: (String) -> Unit = {},
	onBiographyChange: (String) -> Unit = {},
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier
			.padding(14.dp)
	) {
		AvatarAndBackgroundPicker(
			onAvatarPickerClick = onAvatarPickerClick,
			onBackgroundPickerClick = {},
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