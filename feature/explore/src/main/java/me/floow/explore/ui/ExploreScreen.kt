package me.floow.explore.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.floow.uikit.components.topbar.TitleIconTopBar
import me.floow.uikit.R

@Composable
internal fun ExploreScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TitleIconTopBar(
                titleText = "Обзор",
                onClick = { TODO() },
                icon = {
                    NotificationBellIcon(hasBadge = true)
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
            Text(
                "Flow!",
                style = MaterialTheme.typography.titleLarge,
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Explore"
            )
        }
    }
}

@Composable
private fun NotificationBellIcon(
    hasBadge: Boolean = false,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(24.dp)
    ) {
        Icon(
            painterResource(R.drawable.notification_bell),
            null,
            modifier = modifier
        )

        if (hasBadge) {
            Box(
                Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.error)
                    .size(9.dp)
                    .align(Alignment.TopEnd)
            )
        }
    }
}

@Preview
@Composable
private fun ExploreScreenPreview() {
    ExploreScreen(
        modifier = Modifier.fillMaxSize()
    )
}