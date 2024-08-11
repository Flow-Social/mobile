package com.flowme.uikit.components.topbar

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flowme.uikit.R
import com.flowme.uikit.components.buttons.WideOutlinedIconButton
import com.flowme.uikit.theme.LocalTypography
import com.flowme.uikit.util.ComponentPreviewBox

@Composable
fun TitleIconTopBar(
    titleText: String,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = titleText,
                style = LocalTypography.current.titleMedium
            )

            Spacer(Modifier.weight(1f))

            WideOutlinedIconButton(
                onClick = onClick,
                modifier = Modifier
            ) {
                icon()
            }
        }

        HorizontalDivider()
    }
}

@Preview
@Composable
private fun TitleIconTopBarPreview(modifier: Modifier = Modifier) {
    ComponentPreviewBox(Modifier.fillMaxSize()) {
        TitleIconTopBar(
            titleText = "Chats",
            icon = {
                Icon(
                    painterResource(R.drawable.chats_icon),
                    null
                )
            },
            onClick = {},
            modifier = Modifier
                .fillMaxSize()
        )
    }
}