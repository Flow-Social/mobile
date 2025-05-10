package me.floow.chats.ui.chat.components

import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.floow.uikit.theme.LocalTypography
import me.floow.uikit.util.ComponentPreviewBox

@Composable
fun DateSeparator(
	text: String,
	modifier: Modifier = Modifier
) {
	Text(
		text = text,
		style = LocalTypography.current.labelMedium,
		color = MaterialTheme.colorScheme.outline,
		modifier = modifier
			.background(MaterialTheme.colorScheme.surfaceContainer, RoundedCornerShape(20.dp))
			.border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(20.dp))
			.padding(
				horizontal = 10.dp,
				vertical = 8.dp
			)
	)
}

@Preview
@Composable
fun DateSeparatorPreview() {
	ComponentPreviewBox {
		DateSeparator(
			text = "Today",
			modifier = Modifier
		)
	}
}