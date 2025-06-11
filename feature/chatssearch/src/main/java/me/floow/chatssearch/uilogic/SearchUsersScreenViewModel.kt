package me.floow.chatssearch.uilogic

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
import me.floow.mock.data.MockProfileRepository
import kotlin.random.Random

private data class SearchUsersScreenVmState(
	val searchField: String = "",
	val isLoading: Boolean = false,
	val globalSearchResults: List<UserSearchResult>? = null,
	val messageSearchResults: List<MessageResult>? = null,
	val recentUsers: List<RecentUser>? = null,
) {
	fun toUiState(): SearchUsersScreenUiState {
		if (isLoading) return SearchUsersScreenUiState.Loading(searchField)

		if ((searchField.isBlank() || globalSearchResults == null) && recentUsers != null) return SearchUsersScreenUiState.NoSearchInput(
			searchField = searchField,
			recentUsers = recentUsers
		)

		if (globalSearchResults != null && messageSearchResults != null) {
			return SearchUsersScreenUiState.HasResults(
				searchField,
				globalSearchResults,
				messageSearchResults
			)
		}

		return SearchUsersScreenUiState.Loading(searchField)
	}
}

class SearchUsersScreenViewModel : ViewModel() {
	private val profileRepository = MockProfileRepository()
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
					messageSearchResults = generateRandomMessageSearchResults(),
					isLoading = false
				)
			}
		}
	}

	private fun generateRandomMessageSearchResults(): List<MessageResult> {
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
		val messages = listOf(
			"Привет, как дела?",
			"Что нового?",
			"Можем встретиться завтра?",
			"Извините, я опаздываю!",
			"Видел новый фильм?",
			"Давай перекусим вместе.",
			"Мне нужна твоя помощь.",
			"Как прошли выходные?",
			"Есть планы на вечер?",
			"Нашел отличный новый ресторан.",
			"Можешь прислать отчет?",
			"Следующую неделю уезжаю в отпуск.",
			"Хочешь присоединиться к нам на ужин?",
			"Застрял в пробке, скоро буду.",
			"Пробовал новую кофейню?",
			"Нужно перенести нашу встречу.",
			"Получил мое письмо?",
			"Давай встретимся как-нибудь.",
			"С нетерпением жду поездки!",
			"Есть рекомендации по книгам?"
		)

		return List(Random.nextInt(200)) {
			val randomName = names[Random.nextInt(names.size)]
			val randomMessage = messages[Random.nextInt(messages.size)]

			MessageResult(
				name = ProfileName.create(randomName),
				messageText = randomMessage
			)
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
				name = ProfileName.create(randomName),
				username = ProfileUsername.create(randomUsername),
				isOnline = isOnline,
				avatarUrl = profileRepository.getAvatarUrl()
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
				name = ProfileName.create(randomName),
			)
		}
	}
}
