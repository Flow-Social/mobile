package me.floow.profile.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import me.floow.profile.uilogic.profile.ProfileScreenState

@Composable
fun ProfileScreen(
	onProfileEditClick: () -> Unit,
	onAddPostButtonClick: () -> Unit,
	onShareButtonClick: () -> Unit,
	state: ProfileScreenState,
	modifier: Modifier = Modifier,
) {
	Box(
		modifier = modifier
	) {
		when (state) {
			is ProfileScreenState.Loading -> {
				CircularProgressIndicator( // todo
					modifier = Modifier
						.align(Alignment.Center)
				)
			}

			is ProfileScreenState.Error -> {
				Text( // todo
					text = "error :(",
					modifier = Modifier
				)
			}

			is ProfileScreenState.Success -> {
				ProfileScreenSuccessState(
					state = state,
					onProfileEditClick = onProfileEditClick,
					onAddPostButtonClick = onAddPostButtonClick,
					onShareButtonClick = onShareButtonClick,
					modifier = Modifier
				)
			}
		}
	}
}