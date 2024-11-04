package com.demn.usersearch.uilogic

interface SearchUsersScreenUiState {
	val searchField: String

	data class Loading(override val searchField: String) : SearchUsersScreenUiState

	data class EmptyResults(override val searchField: String) : SearchUsersScreenUiState
}