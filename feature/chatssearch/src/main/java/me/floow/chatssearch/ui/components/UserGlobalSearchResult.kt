package me.floow.chatssearch.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.floow.chatssearch.uilogic.UserSearchResult
import me.floow.domain.values.ProfileName
import me.floow.domain.values.ProfileUsername
import me.floow.uikit.R
import me.floow.uikit.theme.ElevanagonShape
import me.floow.uikit.theme.LocalTypography
import me.floow.uikit.util.ComponentPreviewBox

@Composable
fun UserGlobalSearchResult(
	userSearchResult: UserSearchResult,
	onClick: (UserSearchResult) -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier
			.clickable { onClick(userSearchResult) }
			.padding(horizontal = 20.dp, vertical = 4.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Box(
			Modifier
				.size(56.dp)
				.clip(ElevanagonShape)
				.background(Color.LightGray)
		)

		Spacer(Modifier.width(9.dp))

		Column(
			modifier = Modifier,
			verticalArrangement = Arrangement.Center
		) {
			Text(
				text = userSearchResult.name.value,
				style = LocalTypography.current.titleMedium,
			)

			Spacer(Modifier.height(2.dp))

			Text(
				text = stringResource(if (userSearchResult.isOnline) R.string.online else R.string.offline),
				style = LocalTypography.current.bodyMedium,
				color = MaterialTheme.colorScheme.secondary
			)
		}
	}
}

@Preview
@Composable
private fun UserGlobalSearchResultPreview() {
	ComponentPreviewBox(Modifier.fillMaxWidth()) {
		UserGlobalSearchResult(
			userSearchResult = UserSearchResult(
				ProfileName.create("Demn"),
				ProfileUsername.create(
					"demndevel"
				),
				isOnline = false
			),
			onClick = {},
			modifier = Modifier.fillMaxWidth()
		)
	}
}