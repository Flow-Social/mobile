package me.floow.profile.ui.profile.segments.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.floow.uikit.R
import me.floow.uikit.theme.LocalTypography

@Composable
internal fun ProfileButtonsSegment(
	onAddPostButtonClick: () -> Unit,
	onShareButtonClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier
			.padding(vertical = 16.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Column(
			modifier = Modifier
				.padding(horizontal = 16.dp)
				.weight(1f)
				.clip(RoundedCornerShape(4.dp))
				.clickable { onAddPostButtonClick() },
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Icon(
				painter = painterResource(R.drawable.add_post_outline_icon),
				contentDescription = null,
				modifier = Modifier.size(27.dp)
			)

			Spacer(Modifier.height(8.dp))

			Text(
				text = stringResource(me.floow.profile.R.string.new_post),
				style = LocalTypography.current.labelMedium
			)
		}

		VerticalDivider(Modifier.height(18.dp))

		Column(
			modifier = Modifier
				.padding(horizontal = 16.dp)
				.weight(1f)
				.clip(RoundedCornerShape(4.dp))
				.clickable { onShareButtonClick() },
//				.padding(end = 16.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Icon(
				painter = painterResource(R.drawable.share_icon),
				contentDescription = null,
				modifier = Modifier.size(27.dp)
			)

			Spacer(Modifier.height(8.dp))

			Text(
				text = stringResource(me.floow.profile.R.string.share),
				style = LocalTypography.current.labelMedium
			)
		}
	}
}