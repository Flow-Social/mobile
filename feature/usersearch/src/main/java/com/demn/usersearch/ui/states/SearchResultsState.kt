package com.demn.usersearch.ui.states

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.demn.usersearch.ui.components.GlobalSearchUsersList
import com.demn.usersearch.ui.components.UserGlobalSearchResult
import com.demn.usersearch.uilogic.SearchUsersScreenUiState

@Composable
fun SearchResultsState(
	state: SearchUsersScreenUiState.HasResults,
	modifier: Modifier = Modifier
) {
	val context = LocalContext.current

	Column(
		modifier = modifier
	) {
		Spacer(Modifier.height(12.dp))

		GlobalSearchUsersList(
			results = state.results,
			onClick = { result ->
				Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show()
			}
		)
	}
}