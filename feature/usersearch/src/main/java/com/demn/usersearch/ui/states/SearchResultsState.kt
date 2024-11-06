package com.demn.usersearch.ui.states

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.demn.usersearch.ui.components.globalSearchUsersList
import com.demn.usersearch.ui.components.messageResultsList
import com.demn.usersearch.uilogic.SearchUsersScreenUiState

@Composable
fun SearchResultsState(
	state: SearchUsersScreenUiState.HasResults,
	modifier: Modifier = Modifier
) {
	val context = LocalContext.current
	var isGlobalUsersSearchExpanded by remember { mutableStateOf(false) }

	Column(modifier = modifier) {
		LazyColumn(
			modifier = Modifier.fillMaxSize(),
		) {
			globalSearchUsersList(
				isExpanded = isGlobalUsersSearchExpanded,
				onExpandedToggle = { isGlobalUsersSearchExpanded = !isGlobalUsersSearchExpanded },
				results = state.userResults,
				onClick = { result ->
					Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show()
				}
			)

			item {
				Spacer(Modifier.height(12.dp))

				HorizontalDivider()
			}

			messageResultsList(
				results = state.messageResults,
				onClick = { result ->
					Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show()
				}
			)
		}
	}
}