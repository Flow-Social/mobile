package com.flowme.chats.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flowme.uikit.R
import com.flowme.uikit.components.topbar.TitleIconTopBar

@Composable
fun ChatsScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TitleIconTopBar(
                titleText = "Чаты",
                onClick = { TODO() },
                icon = {
                    Icon(
                        painterResource(R.drawable.chats_icon),
                        null
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            Spacer(Modifier.height(24.dp))

            Text(
                text = "Flow!",
                style = MaterialTheme.typography.titleLarge,
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Chats"
            )
        }
    }
}

@Preview
@Composable
private fun ChatsScreenPreview() {
    ChatsScreen(
        modifier = Modifier.fillMaxSize()
    )
}