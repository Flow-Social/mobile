package com.demn.usersearch.uilogic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.floow.domain.values.ProfileName
import me.floow.domain.values.ProfileUsername
import kotlin.random.Random

private data class SearchUsersScreenVmState(
	val searchField: String = "",
	val isLoading: Boolean = false,
	val globalSearchResults: List<UserSearchResult>? = null,
	val recentUsers: List<RecentUser>? = null,
) {
	fun toUiState(): SearchUsersScreenUiState {
		if (isLoading) return SearchUsersScreenUiState.Loading(searchField)

		if ((searchField.isBlank() || globalSearchResults == null) && recentUsers != null) return SearchUsersScreenUiState.NoSearchInput(
			searchField = searchField,
			recentUsers = recentUsers
		)

		if (globalSearchResults != null) {
			return SearchUsersScreenUiState.HasResults(
				searchField,
				globalSearchResults,
			)
		}

		return SearchUsersScreenUiState.Loading(searchField)
	}
}

class SearchUsersScreenViewModel : ViewModel() {
	private val _state = MutableStateFlow(SearchUsersScreenVmState())

	val state: StateFlow<SearchUsersScreenUiState> = _state
		.map(SearchUsersScreenVmState::toUiState)
		.stateIn(
			viewModelScope,
			SharingStarted.Eagerly,
			SearchUsersScreenUiState.NoSearchInput("", emptyList())
		)

	fun loadInitialData() {
		viewModelScope.launch {
			_state.update {
				it.copy(
					isLoading = true
				)
			}

			delay(100L)

			_state.update {
				it.copy(
					recentUsers = generateRandomRecentUsers(),
					isLoading = false
				)
			}
		}
	}

	fun updateSearchField(newValue: String) {
		_state.update {
			it.copy(
				searchField = newValue
			)
		}

		viewModelScope.launch {
			_state.update {
				it.copy(
					isLoading = true
				)
			}

			delay(300L)

			_state.update {
				it.copy(
					globalSearchResults = generateRandomUserSearchResults(),
					isLoading = false
				)
			}
		}
	}

	private fun generateRandomUserSearchResults(): List<UserSearchResult> {
		val names = listOf(
			"Alice",
			"Bob",
			"Charlie",
			"David",
			"Eve",
			"Frank",
			"Grace",
			"Heidi",
			"Ivan",
			"Judy"
		)
		val usernames = listOf(
			"user1",
			"user2",
			"user3",
			"user4",
			"user5",
			"user6",
			"user7",
			"user8",
			"user9",
			"user10"
		)

		return List(Random.nextInt(10)) {
			val randomName = names[Random.nextInt(names.size)]
			val randomUsername = usernames[Random.nextInt(usernames.size)]
			val isOnline = Random.nextBoolean()

			UserSearchResult(
				name = ProfileName(randomName),
				username = ProfileUsername(randomUsername),
				isOnline = isOnline
			)
		}
	}

	private fun generateRandomRecentUsers(): List<RecentUser> {
		val names = listOf(
			"Alice",
			"Bob",
			"Charlie",
			"David",
			"Eve",
			"Frank",
			"Grace",
			"Heidi",
			"Ivan",
			"Judy"
		)

		return List(Random.nextInt(15)) {
			val randomName = names[Random.nextInt(names.size)]

			RecentUser(
				name = ProfileName(randomName),
			)
		}
	}
}