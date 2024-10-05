package me.floow.profile.ui

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import me.floow.uikit.R
import me.floow.uikit.components.topbar.TitleTopBarWithActionButton

@Composable
internal fun ProfileScreenTopBar(username: String, onProfileEditClick: () -> Unit, modifier: Modifier = Modifier) {
	TitleTopBarWithActionButton(
		titleText = "@$username",
		onActionButtonClick = onProfileEditClick,
		icon = {
			Icon(
				painter = painterResource(R.drawable.edit_icon),
				contentDescription = null,
			)
		},
		modifier = modifier
	)
}
