package me.floow.profile.ui.segments.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.floow.uikit.R

@Composable
internal fun ProfileButtonsSegment(
	onAddPostButtonClick: () -> Unit,
	onShareButtonClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier
			.height(75.dp),
		horizontalArrangement = Arrangement
			.spacedBy(78.dp, Alignment.CenterHorizontally),
		verticalAlignment = Alignment.CenterVertically
	) {
		IconButton(onClick = onAddPostButtonClick) {
			Icon(
				painter = painterResource(R.drawable.add_post_outline_icon),
				contentDescription = null,
				modifier = Modifier.size(27.dp)
			)
		}

		VerticalDivider(Modifier.height(18.dp))

		IconButton(onClick = onShareButtonClick) {
			Icon(
				painter = painterResource(R.drawable.share_icon),
				contentDescription = null,
				modifier = Modifier.size(27.dp)
			)
		}
	}
}