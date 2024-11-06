package com.demn.usersearch.uilogic

import me.floow.domain.values.ProfileName
import me.floow.domain.values.ProfileUsername

data class UserSearchResult(
	val name: ProfileName,
	val username: ProfileUsername,
	val isOnline: Boolean
)

data class RecentUser(
	val name: ProfileName,
)

interface SearchUsersScreenUiState {
	val searchField: String

	data class Loading(override val searchField: String) : SearchUsersScreenUiState

	data class NoSearchInput(
		override val searchField: String,
		val recentUsers: List<RecentUser>
	) : SearchUsersScreenUiState

	data class HasResults(
		override val searchField: String,
		val results: List<UserSearchResult>
	) : SearchUsersScreenUiState
}