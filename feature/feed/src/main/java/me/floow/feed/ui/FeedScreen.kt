package me.floow.feed.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.floow.uikit.R
import me.floow.uikit.components.misc.BlankContentBox
import me.floow.uikit.components.topbar.TitleTopBarWithActionButton

@Composable
internal fun FeedScreen(
	onPostCreateClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	Scaffold(
		topBar = {
			TitleTopBarWithActionButton(
				titleText = stringResource(R.string.feed_topbar_title),
				onActionButtonClick = onPostCreateClick,
				icon = {
					Icon(
						painter = painterResource(R.drawable.plus_icon),
						contentDescription = null
					)
				}
			)
		},
		modifier = modifier
	) { innerPadding ->
		Column(
			Modifier
				.padding(8.dp)
				.padding(innerPadding)
		) {
			BlankContentBox(Modifier.fillMaxSize())
		}
	}
}

@Preview
@Composable
private fun FeedScreenPreview() {
	FeedScreen(
		onPostCreateClick = {},
		modifier = Modifier.fillMaxSize()
	)
}
