package me.floow.uikit.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.floow.uikit.R
import me.floow.uikit.theme.LocalTypography

@Composable
fun SearchTopBar(
	onBackClick: () -> Unit,
	placeholder: String,
	searchFieldValue: String,
	onSearchFieldUpdate: (String) -> Unit,
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.height(80.dp)
				.padding(horizontal = 24.dp),
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

			BasicTextField(
				value = searchFieldValue,
				onValueChange = onSearchFieldUpdate,
				singleLine = true,
				cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
				textStyle = LocalTypography.current.bodyMedium.copy(
					color = MaterialTheme.colorScheme.onBackground
				),
				decorationBox = { innerTextField ->
					Row(
						modifier = Modifier
							.padding(
								horizontal = 14.dp,
								vertical = 20.dp
							)
							.fillMaxWidth()
					) {
						Box(Modifier.fillMaxWidth()) {
							innerTextField()

							if (searchFieldValue.isEmpty()) {
								Text(
									text = placeholder,
									style = LocalTypography.current.bodyMedium,
									color = MaterialTheme.colorScheme.secondary,
									modifier = Modifier
								)
							}
						}
					}
				},
				modifier = Modifier
					.weight(1f)
					.height(58.dp)
			)
		}

		HorizontalDivider()
	}
}

@Preview
@Composable
private fun SearchTopBarPreview() {
	var searchBarValue by remember { mutableStateOf("") }

	Scaffold(
		topBar = {
			SearchTopBar(
				onBackClick = {},
				searchFieldValue = searchBarValue,
				placeholder = "Search…",
				onSearchFieldUpdate = { searchBarValue = it }
			)
		},
		modifier = Modifier.fillMaxSize()
	) { innerPadding ->
		Box(
			Modifier
				.padding(innerPadding)
				.fillMaxSize()
				.background(Color.LightGray)
		)
	}
}