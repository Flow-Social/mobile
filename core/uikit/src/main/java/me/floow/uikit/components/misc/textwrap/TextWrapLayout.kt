package me.floow.uikit.components.misc.textwrap

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit


@Composable
internal fun TextWrapLayout(
    text: AnnotatedString,
    style: TextStyle,
    modifier: Modifier = Modifier,
    forcedObstacleOffset: IntOffset,
    obstacleAlignment: TextWrapObstacleAlignment,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign = TextAlign.Unspecified,
    lineHeight: TextUnit = TextUnit.Unspecified,
    softWrap: Boolean = true,
    obstacleContent: @Composable () -> Unit = {},
) {
    SubcomposeLayout(modifier) { constraints ->
        val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)

        val obstaclePlaceables = subcompose(TextWrapContent.Obstacle, obstacleContent).map {
            it.measure(looseConstraints)
        }

        val maxObstacleWidth = obstaclePlaceables.maxOfOrNull { it.width } ?: 0
        val maxObstacleHeight = obstaclePlaceables.maxOfOrNull { it.height } ?: 0

        val textPlaceable = subcompose(TextWrapContent.Text) {
            TextWrapCanvas(
                text = text,
                obstacleSize = IntSize(maxObstacleWidth, maxObstacleHeight),
                obstacleAlignment = obstacleAlignment,
                constraints = constraints,
                color = color,
                fontSize = fontSize,
                fontStyle = fontStyle,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                letterSpacing = letterSpacing,
                textDecoration = textDecoration,
                textAlign = textAlign,
                lineHeight = lineHeight,
                softWrap = softWrap,
                style = style,
            )
        }.first().measure(looseConstraints)

        val obstaclePlacementOffset = when (obstacleAlignment) {
            TextWrapObstacleAlignment.TopStart -> IntOffset.Zero

            TextWrapObstacleAlignment.TopEnd -> IntOffset(
                textPlaceable.width - maxObstacleWidth,
                0,
            )

            TextWrapObstacleAlignment.BottomStart -> IntOffset(
                0,
                textPlaceable.height - maxObstacleHeight,
            )

            TextWrapObstacleAlignment.BottomEnd -> IntOffset(
                textPlaceable.width - maxObstacleWidth,
                textPlaceable.height - maxObstacleHeight,
            )
        }

        layout(
            width = textPlaceable.width,
            height = textPlaceable.height,
        ) {
            obstaclePlaceables.forEach {
                it.place(obstaclePlacementOffset.plus(forcedObstacleOffset))
            }

            textPlaceable.place(0, 0)
        }
    }
}

private enum class TextWrapContent { Obstacle, Text }