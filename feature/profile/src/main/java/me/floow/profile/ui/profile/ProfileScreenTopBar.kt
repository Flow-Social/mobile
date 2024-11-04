package me.floow.profile.ui.profile

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import me.floow.uikit.R
import me.floow.uikit.components.topbar.TitleTopBarWithActionButton

@Composable
internal fun ProfileScreenTopBar(username: String?, onProfileEditClick: () -> Unit, modifier: Modifier = Modifier) {
	TitleTopBarWithActionButton(
		titleText = username?.let { "@$it" } ?: stringResource(me.floow.profile.R.string.no_username_topbar_title),
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
