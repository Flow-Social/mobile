package com.demn.usersearch.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.demn.usersearch.uilogic.MessageResult
import me.floow.uikit.theme.LocalTypography
import me.floow.usersearch.R

fun LazyListScope.messageResultsList(
	results: List<MessageResult>,
	onClick: (MessageResult) -> Unit,
) {
	item {
		Spacer(Modifier.height(12.dp))

		Row(
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 14.dp),
		) {
			Text(
				text = stringResource(R.string.message_search),
				style = LocalTypography.current.bodyMedium,
				fontWeight = FontWeight.Bold
			)
		}

		Spacer(Modifier.height(12.dp))
	}

	items(results) { result ->
		MessageResult(
			result = result,
			onClick = onClick,
			modifier = Modifier
				.fillMaxWidth()
		)
	}
}