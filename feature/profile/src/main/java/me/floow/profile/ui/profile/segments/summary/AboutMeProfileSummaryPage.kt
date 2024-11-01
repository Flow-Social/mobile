package me.floow.profile.ui.profile.segments.summary

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.floow.profile.R
import me.floow.uikit.theme.LocalTypography

@Composable
internal fun AboutMeProfileSummaryPage(description: String?, modifier: Modifier = Modifier) {
	Column(
		modifier = modifier
			.padding(horizontal = 24.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = "обо мне",
			style = LocalTypography.current.titleMedium,
			color = Color.White,
		)

		Spacer(Modifier.height(9.dp))

		Text(
			text = description ?: stringResource(R.string.no_profile_description),
			style = LocalTypography.current.bodyMedium,
			color = Color.White,
		)
	}
}