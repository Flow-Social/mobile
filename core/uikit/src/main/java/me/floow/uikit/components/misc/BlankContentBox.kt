package me.floow.uikit.components.misc

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.floow.uikit.R
import me.floow.uikit.theme.LocalTypography

@Composable
fun BlankContentBox(modifier: Modifier = Modifier) {
	Box(
		contentAlignment = Alignment.Center,
		modifier = modifier
	) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			modifier = Modifier
		) {
			Image(
				painter = painterResource(R.drawable.blank_girl),
				contentDescription = null,
			)

			Spacer(Modifier.height(24.dp))

			Text(
				text = "Ничего нет.",
				style = LocalTypography.current.titleLarge.copy(
					fontSize = 24.sp,
				),
			)

			Spacer(Modifier.height(10.dp))

			Text(
				text = "Но скоро будет, обещаем!",
				style = LocalTypography.current.bodyMedium,
			)
		}
	}
}

@Preview
@Composable
private fun BlankContentBoxPreview() {
	BlankContentBox(
		modifier = Modifier
			.fillMaxSize()
	)
}
