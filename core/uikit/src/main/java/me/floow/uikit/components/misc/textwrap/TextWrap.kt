package me.floow.uikit.components.misc.textwrap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp


/**
 * Компонент текста, обтекающего вокруг препятствия
 * @param[text] обтекающий текст
 * @param[obstacleAlignment] расположение препятствия
 * @param[forcedObstacleOffset] дополнительный отступ для препятствия. Forced, так как
 * применяется после расположения текста и игнорирует размеры итогового компонента
 * @param[obstacleContent] препятствие
 */
@Composable
fun TextWrap(
    text: String,
    modifier: Modifier = Modifier,
    obstacleAlignment: TextWrapObstacleAlignment,
    forcedObstacleOffset: IntOffset = IntOffset.Zero,
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
    style: TextStyle = LocalTextStyle.current,
    obstacleContent: @Composable () -> Unit = {},
) {
    val textColor = color.takeOrElse {
        style.color.takeOrElse {
            LocalContentColor.current
        }
    }

    TextWrapLayout(
        text = AnnotatedString(text),
        style = style,
        modifier = modifier,
        obstacleAlignment = obstacleAlignment,
        color = textColor,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        softWrap = softWrap,
        obstacleContent = obstacleContent,
        forcedObstacleOffset = forcedObstacleOffset,
    )
}

/**
 * Расположение препятствия в обтекающем тексте
 */
enum class TextWrapObstacleAlignment {
    /** Левый верхний угол */
    TopStart,

    /** Правый верхний угол */
    TopEnd,

    /** Левый нижний угол */
    BottomStart,

    /** Правый нижний угол */
    BottomEnd,
}

@Preview(showBackground = true)
@Composable
private fun TextWrapPreview() {
    val text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus porttitor tortor sit amet tortor varius congue. Nullam sit amet leo quis nisi faucibus pellentesque a at eros. Nullam dolor turpis, suscipit pellentesque vestibulum finibus, gravida cursus tellus. Pellentesque commodo sapien elementum, tempus sapien vitae, hendrerit augue. Nulla eget neque vulputate, consequat odio et, ultricies arcu. Curabitur aliquet pretium luctus"
    val obstacleContent: @Composable () -> Unit = {
        Box(
            Modifier
                .height(30.dp)
                .width(70.dp)
                .background(color = Color.Red)
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(32.dp)) {
        // Препятствие в левом верхнем углу
        TextWrap(
            text = text,
            obstacleAlignment = TextWrapObstacleAlignment.TopStart,
            obstacleContent = obstacleContent
        )
        // Препятствие в правом верхнем углу
        TextWrap(
            text = text,
            obstacleAlignment = TextWrapObstacleAlignment.TopEnd,
            obstacleContent = obstacleContent
        )
        // Препятствие в левом нижнем углу
        TextWrap(
            text = text,
            obstacleAlignment = TextWrapObstacleAlignment.BottomStart,
            obstacleContent = obstacleContent
        )
        // Препятствие в правом нижнем углу
        TextWrap(
            text = text,
            obstacleAlignment = TextWrapObstacleAlignment.BottomEnd,
            obstacleContent = obstacleContent
        )
    }
}

