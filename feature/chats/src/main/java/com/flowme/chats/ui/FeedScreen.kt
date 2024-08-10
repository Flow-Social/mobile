package com.flowme.chats.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatsScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .padding(8.dp)
    ) {
        Text(
            "Flow!",
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = "Chats"
        )
    }
}