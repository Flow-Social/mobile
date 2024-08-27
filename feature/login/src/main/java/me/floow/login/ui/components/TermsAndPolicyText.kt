package me.floow.login.ui.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import me.floow.uikit.R
import me.floow.uikit.theme.FlowCustomTheme

@Composable
internal fun TermsAndPolicyText(
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val privacyPolicyString = stringResource(R.string.privacy_policy)
    val termsAndConditionsString = stringResource(R.string.terms_and_conditions)

    val annotatedString = buildAnnotatedString {
        withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
            append(stringResource(R.string.auth_accepting_by_continuing))

            pushStringAnnotation(tag = "terms_and_conditions", annotation = "terms_and_conditions")
            withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                append(termsAndConditionsString)
            }
            pop()

            append(stringResource(R.string.auth_accepting_and))

            pushStringAnnotation(tag = "privacy_policy", annotation = "privacy_policy")
            withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                append(privacyPolicyString)
            }
            pop()

            append(stringResource(R.string.auth_accepting_will_be_applied))
        }
    }

    ClickableText(
        annotatedString,
        style = FlowCustomTheme.typography.labelMedium.copy(textAlign = TextAlign.Center),
        modifier = modifier
    ) { offset ->
        annotatedString.getStringAnnotations(tag = "terms_and_conditions", start = offset, end = offset).firstOrNull()
            ?.let { onTermsClick() }

        annotatedString.getStringAnnotations(tag = "privacy_policy", start = offset, end = offset).firstOrNull()
            ?.let { onPrivacyClick() }
    }
}