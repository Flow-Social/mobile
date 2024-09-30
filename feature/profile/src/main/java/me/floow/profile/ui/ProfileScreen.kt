package me.floow.profile.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.floow.profile.uilogic.ProfileScreenState

@Composable
fun ProfileScreen(
	state: ProfileScreenState,
	modifier: Modifier = Modifier,
) {
	Scaffold(
		modifier = modifier
	) { innerPadding ->
		when (state) {
			is ProfileScreenState.Loading -> {
				CircularProgressIndicator(
					modifier = Modifier
						.padding(innerPadding)
				)
			}

			is ProfileScreenState.Error -> {
				Text(
					text = "error :(",
					modifier = Modifier
						.padding(innerPadding)
				)
			}

			is ProfileScreenState.Success -> {
				Text(
					text = """
				hihihihi
				$state  
				""".trimIndent(),
					modifier = Modifier
						.padding(innerPadding)
				)
			}
		}
	}
}