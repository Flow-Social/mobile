package me.floow.uikit.components.misc

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.floow.uikit.R
import me.floow.uikit.theme.LocalTypography

private val curiousPicsList = listOf(
	R.drawable.curious_pic_a,
	R.drawable.curious_pic_b,
	R.drawable.curious_pic_c,
	R.drawable.curious_pic_d,
)

@Composable
fun ErrorWithButtonContentBox(
	title: String,
	description: String,
	onButtonClick: () -> Unit,
	buttonContent: @Composable () -> Unit,
	modifier: Modifier = Modifier
) {
	val curiousPicResource = remember { curiousPicsList.random() }

	Box(
		contentAlignment = Alignment.Center,
		modifier = modifier
	) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			modifier = Modifier.fillMaxWidth()
		) {
			Image(
				painter = painterResource(curiousPicResource),
				contentDescription = null,
				modifier = Modifier
					.size(164.dp)
			)

			Spacer(Modifier.height(10.dp))

			Text(
				text = title,
				style = LocalTypography.current.titleLarge.copy(
					fontSize = 24.sp,
				),
			)

			Spacer(Modifier.height(10.dp))

			Text(
				text = description,
				style = LocalTypography.current.bodyMedium,
			)

			Spacer(Modifier.height(20.dp))

			FilledTonalButton(
				onClick = onButtonClick,
				modifier = Modifier,
			) {
				buttonContent()
			}
		}
	}
}

@Preview
@Composable
private fun NotFoundScreenPreview() {
	Surface(
		modifier = Modifier
			.fillMaxSize()
	) {
		ErrorWithButtonContentBox(
			title = "Страница не найдена :(",
			description = "Страницы не существует или она была удалена.",
			onButtonClick = {},
			buttonContent = {
				Text("Назад", color = MaterialTheme.colorScheme.primary)
			},
			modifier = Modifier
				.fillMaxSize()
		)
	}
}
