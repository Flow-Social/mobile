package com.flowme.feed.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flowme.uikit.R
import com.flowme.uikit.components.topbar.ProfileTopBar
import com.flowme.uikit.components.topbar.TitleIconTopBar
import com.flowme.uikit.theme.ElevanagonShape

@Composable
fun FeedScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            ProfileTopBar(
                profileUsername = "__Alin04k@__",
                onEditProfileClick = { TODO() },
                profileAvatar = { profileAvatarModifier ->
                    Image(
                        painterResource(R.drawable.cute_girl),
                        null,
                        profileAvatarModifier
                            .clip(ElevanagonShape),
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier
                .padding(8.dp)
                .padding(innerPadding)
        ) {
            Text(
                "Flow!",
                style = MaterialTheme.typography.titleLarge,
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Feed"
            )
        }
    }
}

@Preview
@Composable
private fun FeedScreenPreview() {
    FeedScreen(Modifier.fillMaxSize())
}