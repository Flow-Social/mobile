package com.demn.usersearch.uilogic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

private data class SearchUsersScreenVmState(
	val searchField: String = ""
) {
	fun toUiState(): SearchUsersScreenUiState {
		return SearchUsersScreenUiState.EmptyResults(searchField = searchField)
	}
}

class SearchUsersScreenViewModel : ViewModel() {
	private val _state = MutableStateFlow(SearchUsersScreenVmState())

	val state: StateFlow<SearchUsersScreenUiState> = _state
		.map(SearchUsersScreenVmState::toUiState)
		.stateIn(viewModelScope, SharingStarted.Eagerly, SearchUsersScreenUiState.Loading(""))
}