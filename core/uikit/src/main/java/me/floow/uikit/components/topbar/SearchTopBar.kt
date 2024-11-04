package me.floow.uikit.components.topbar

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Constraints

/**
 * <a href="https://m3.material.io/components/search/overview" class="external"
 * target="_blank">Material Design search</a>.
 *
 * A search bar represents a floating search field that allows users to enter a keyword or phrase
 * and get relevant information. It can be used as a way to navigate through an app via search
 * queries.
 *
 * A search bar expands into a search "view" and can be used to display dynamic suggestions or
 * search results.
 *
 * @see <img src="https://developer.android.com/images/reference/androidx/compose/material3/search-bar.png"/>
 *
 * A [SearchBar] tries to occupy the entirety of its allowed size in the expanded state. For
 * full-screen behavior as specified by Material guidelines, parent layouts of the [SearchBar] must
 * not pass any [Constraints] that limit its size, and the host activity should set
 * `WindowCompat.setDecorFitsSystemWindows(window, false)`.
 *
 * If this expansion behavior is undesirable, for example on large tablet screens, [DockedSearchBar]
 * can be used instead.
 *
 * An example looks like:
 *
 * @sample androidx.compose.material3.samples.SearchBarSample
 *
 * @param inputField the input field of this search bar that allows entering a query, typically a
 *   [SearchBarDefaults.InputField].
 * @param expanded whether this search bar is expanded and showing search results.
 * @param onExpandedChange the callback to be invoked when this search bar's expanded state is
 *   changed.
 * @param modifier the [Modifier] to be applied to this search bar.
 * @param shape the shape of this search bar when it is not [expanded]. When [expanded], the shape
 *   will always be [SearchBarDefaults.fullScreenShape].
 * @param colors [SearchBarColors] that will be used to resolve the colors used for this search bar
 *   in different states. See [SearchBarDefaults.colors].
 * @param tonalElevation when [SearchBarColors.containerColor] is [ColorScheme.surface], a
 *   translucent primary color overlay is applied on top of the container. A higher tonal elevation
 *   value will result in a darker color in light theme and lighter color in dark theme. See also:
 *   [Surface].
 * @param shadowElevation the elevation for the shadow below this search bar
 * @param windowInsets the window insets that this search bar will respect
 * @param content the content of this search bar to display search results below the [inputField].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
	searchFieldValue: String,
	onSearchFieldUpdate: (String) -> Unit,
	modifier: Modifier = Modifier
) {
	SearchBar(
		inputField = {
			TextField(
				value = searchFieldValue,
				onValueChange = onSearchFieldUpdate,
			)
		},
		expanded = false,
		onExpandedChange = {},
	) {

	}
}