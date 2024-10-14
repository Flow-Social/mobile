package me.floow.uikit.components.input

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.floow.uikit.theme.LocalTypography
import me.floow.uikit.util.ComponentPreviewBox

@Composable
fun TextFieldWithAdditionalText(
    title: String,
    additionalText: String,
    value: String,
    onValueChange: (String) -> Unit,
    minLines: Int = 1,
    maxLines: Int = 1,
    singleLine: Boolean = true,
    placeholder: String = "",
    modifier: Modifier = Modifier
) {
    val outlineVariant = MaterialTheme.colorScheme.outlineVariant
    val outline = MaterialTheme.colorScheme.outline

    var borderColor by remember { mutableStateOf(outlineVariant) }
    var borderWidth by remember { mutableStateOf(1.dp) }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        minLines = minLines,
        maxLines = maxLines,
        singleLine = singleLine,
        textStyle = LocalTypography.current.bodyMedium,
        decorationBox = { innerTextField ->
            Column(
                modifier = Modifier
                    .border(
                        width = borderWidth,
                        color = borderColor,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .padding(14.dp)
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.outline,
                    style = LocalTypography.current.labelMedium,
                    modifier = Modifier
                )

                Spacer(Modifier.height(4.dp))

                Row(Modifier) {
                    Text(
                        text = additionalText,
                        style = LocalTypography.current.bodyMedium,
                    )

                    Box {
                        innerTextField()

                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                style = LocalTypography.current.bodyMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }
            }
        },
        modifier = modifier
            .onFocusChanged {
                if (it.isFocused) {
                    borderWidth = (1.5).dp
                    borderColor = outline
                } else {
                    borderWidth = 1.dp
                    borderColor = outlineVariant
                }
            }
    )
}

@Preview
@Composable
fun TextFieldWithAdditionalTextPreview() {
    var value by remember { mutableStateOf("demn") }
    ComponentPreviewBox(Modifier.size(400.dp)) {
        TextFieldWithAdditionalText(
            title = "Username",
            additionalText = "floow.me/",
            value = value,
            placeholder = "username",
            onValueChange = { value = it },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun TextFieldWithAdditionalTextPreview_WithoutEnteredText() {
    var value by remember { mutableStateOf("") }

    ComponentPreviewBox(Modifier.size(400.dp)) {
        Column(Modifier.fillMaxSize()) {
            TextFieldWithAdditionalText(
                title = "Username",
                additionalText = "floow.me/",
                value = value,
                placeholder = "username",
                onValueChange = { value = it },
                modifier = Modifier.fillMaxWidth()
            )

            TextFieldWithAdditionalText(
                title = "Username",
                additionalText = "floow.me/",
                value = "value",
                placeholder = "username",
                onValueChange = { },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun TextFieldWithAdditionalTextPreview_WithoutAdditionalText() {
    var value by remember { mutableStateOf("") }

    ComponentPreviewBox(Modifier.size(400.dp)) {
        TextFieldWithAdditionalText(
            title = "Username",
            additionalText = "",
            value = value,
            onValueChange = { value = it },
            singleLine = false,
            maxLines = 5,
            modifier = Modifier.fillMaxWidth()
        )
    }
}