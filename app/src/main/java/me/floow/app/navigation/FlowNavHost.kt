package me.floow.app.navigation

import android.content.Intent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import me.floow.chats.ui.ChatsRoute
import me.floow.feed.ui.FeedRoute
import me.floow.login.ui.createprofile.CreateProfileRoute
import me.floow.login.ui.login.LoginRoute
import me.floow.profile.ui.edit.EditProfileRoute
import me.floow.profile.ui.profile.ProfileRoute
import org.koin.androidx.compose.koinViewModel

@Composable
fun FlowNavHost(
	navController: NavHostController,
	startDestination: String,
	modifier: Modifier = Modifier,
) {
	val context = LocalContext.current

	NavHost(navController = navController, startDestination = startDestination) {
		navigation(
			route = NavigationItem.Auth.route,
			startDestination = NavigationItem.Auth.Login.route
		) {
			composable(NavigationItem.Auth.Registration.route) {
				Text(text = "Registration")
			}

			composable(NavigationItem.Auth.CreateProfile.route) {
				CreateProfileRoute(
					onDone = {
						navController.navigate(NavigationItem.Main.route) {
							popUpTo(NavigationItem.Auth.route) { inclusive = false }
						}
					},
					vm = koinViewModel(),
					modifier = modifier
				)
			}

			composable(NavigationItem.Auth.Login.route) {
				LoginRoute(
					onGoToHome = {
						navController.navigate(NavigationItem.Main.route) {
							popUpTo(NavigationItem.Auth.route) { inclusive = false }
						}
					},
					onGoToCreateProfile = {
						navController.navigate(NavigationItem.Auth.CreateProfile.route) {
							popUpTo(NavigationItem.Auth.route) { inclusive = false }
						}
					},
					viewModel = koinViewModel(),
					modifier = modifier
				)
			}
		}

		navigation(
			route = NavigationItem.Main.route,
			startDestination = NavigationItem.Main.Feed.route
		) {
			composable(NavigationItem.Main.Feed.route) {
				FeedRoute(onPostCreateClick = { TODO() }, modifier)
			}

			composable(NavigationItem.Main.EditProfile.route) {
				EditProfileRoute(
					onBackClick = {
						navController.popBackStack()
					},
					onDoneClick = {
						navController.popBackStack()
					},
					vm = koinViewModel(),
					modifier = modifier
				)
			}

			composable(NavigationItem.Main.Profile.route) {
				ProfileRoute(
					goToProfileEditScreen = { navController.navigate(NavigationItem.Main.EditProfile.route) },
					goToAddPostScreen = { TODO() },
					shareProfile = { url ->
						// TODO

						val sendIntent = Intent().apply {
							setAction(Intent.ACTION_SEND)
							putExtra(Intent.EXTRA_TEXT, url)
							setType("text/plain")
						}

						val shareIntent = Intent.createChooser(sendIntent, null)
						context.startActivity(shareIntent)
					},
					viewModel = koinViewModel(),
					modifier = modifier
				)
			}

			composable(NavigationItem.Main.Chats.route) {
				ChatsRoute(modifier)
			}
		}
	}
}