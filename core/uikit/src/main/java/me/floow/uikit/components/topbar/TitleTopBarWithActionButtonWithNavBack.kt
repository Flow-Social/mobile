package me.floow.uikit.components.topbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.floow.uikit.R
import me.floow.uikit.components.buttons.WideOutlinedIconButton
import me.floow.uikit.theme.LocalTypography
import me.floow.uikit.util.ComponentPreviewBox

@Composable
fun TitleTopBarWithActionButtonWithNavBack(
	titleText: String,
	onBackClick: () -> Unit,
	onActionButtonClick: () -> Unit,
	icon: @Composable () -> Unit,
	modifier: Modifier = Modifier
) {
	Column(modifier) {
		Row(
			Modifier
				.fillMaxWidth()
				.height(80.dp)
				.padding(horizontal = 24.dp),
			verticalAlignment = Alignment.CenterVertically,
		) {
			IconButton(
				onClick = onBackClick,
				modifier = Modifier.size(24.dp)
			) {
				Icon(
					painter = painterResource(R.drawable.nav_back_icon),
					contentDescription = null,
					modifier = Modifier.size(16.dp)
				)
			}

			Spacer(Modifier.width(10.dp))

			Text(
				text = titleText,
				style = LocalTypography.current.titleLarge
			)

			Spacer(Modifier.weight(1f))

			WideOutlinedIconButton(
				onClick = onActionButtonClick,
				modifier = Modifier
			) {
				icon()
			}
		}

		HorizontalDivider()
	}
}

@Preview
@Composable
private fun TitleTopBarWithActionButtonWithNavBackPreview(modifier: Modifier = Modifier) {
	ComponentPreviewBox(Modifier.fillMaxSize()) {
		TitleTopBarWithActionButtonWithNavBack(
			titleText = "Chats",
			onBackClick = {},
			icon = {
				Icon(
					painterResource(R.drawable.chats_icon),
					null
				)
			},
			onActionButtonClick = {},
			modifier = Modifier
				.fillMaxSize()
		)
	}
}