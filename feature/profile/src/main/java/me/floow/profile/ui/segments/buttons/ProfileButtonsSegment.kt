package me.floow.profile.ui.segments.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
			.padding(16.dp),
//		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth(0.5f)
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

//		VerticalDivider(Modifier.height(18.dp))

		Column(
			modifier = Modifier
				.fillMaxWidth(0.5f)
				.clip(RoundedCornerShape(4.dp))
				.clickable { onShareButtonClick() },
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