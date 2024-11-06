package com.demn.usersearch.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import me.floow.uikit.components.topbar.SearchTopBar
import me.floow.usersearch.R

@Composable
fun SearchUsersScreenTopBar(
	onBackClick: () -> Unit,
	searchFieldValue: String,
	onSearchFieldUpdate: (String) -> Unit,
	modifier: Modifier = Modifier
) {
	SearchTopBar(
		onBackClick = onBackClick,
		searchFieldValue = searchFieldValue,
		placeholder = stringResource(R.string.search_field_placeholder),
		onSearchFieldUpdate = onSearchFieldUpdate,
		modifier = modifier
	)
}