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
import androidx.navigation.toRoute
import com.demn.usersearch.ui.SearchUsersRoute
import me.floow.chats.ui.ChatsRoute
import me.floow.feed.ui.FeedRoute
import me.floow.login.ui.createprofile.CreateProfileRoute
import me.floow.login.ui.login.LoginRoute
import me.floow.profile.ui.edit.EditProfileRoute
import me.floow.profile.ui.edit.EditProfileRouteInitialData
import me.floow.profile.ui.profile.ProfileRoute
import org.koin.androidx.compose.koinViewModel

@Composable
fun FlowNavHost(
	navController: NavHostController,
	startDestination: NavigationRoute,
	modifier: Modifier = Modifier,
) {
	val context = LocalContext.current

	NavHost(navController = navController, startDestination = startDestination) {
		navigation<AuthDestinationsCluster>(
			startDestination = LoginScreen
		) {
			composable<RegistrationScreen> {
				Text(text = "Registration")
			}

			composable<CreateProfileScreen> {
				CreateProfileRoute(
					onDone = {
						navController.navigate(MainDestinationsCluster) {
							popUpTo<AuthDestinationsCluster> { inclusive = false }
						}
					},
					vm = koinViewModel(),
					modifier = modifier
				)
			}

			composable<LoginScreen> {
				LoginRoute(
					onGoToHome = {
						navController.navigate(MainDestinationsCluster) {
							popUpTo<AuthDestinationsCluster> { inclusive = false }
						}
					},
					onGoToCreateProfile = {
						navController.navigate(CreateProfileScreen) {
							popUpTo<AuthDestinationsCluster> { inclusive = false }
						}
					},
					viewModel = koinViewModel(),
					modifier = modifier
				)
			}
		}

		navigation<MainDestinationsCluster>(
			startDestination = FeedScreen
		) {
			composable<FeedScreen> {
				FeedRoute(onPostCreateClick = { TODO() }, modifier)
			}

			composable<EditProfileScreen> {
				val backStackEntry = navController.currentBackStackEntry
				val editProfileScreen: EditProfileScreen? = backStackEntry?.toRoute<EditProfileScreen>()

				EditProfileRoute(
					initialData = EditProfileRouteInitialData(
						name = editProfileScreen?.name ?: "",
						username = editProfileScreen?.username ?: "",
						description = editProfileScreen?.description ?: "",
					),
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

			composable<ProfileScreen> {
				ProfileRoute(
					goToProfileEditScreen = { name, username, description ->
						navController.navigate(
							EditProfileScreen(
								name = name,
								username = username,
								description = description
							)
						)
					},
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

			composable<ChatsScreen> {
				ChatsRoute(
					onSearchClick = {
						navController.navigate(SearchUsersScreen)
					},
					modifier = modifier
				)
			}

			composable<SearchUsersScreen> {
				SearchUsersRoute(
					onUserPick = { TODO() },
					vm = koinViewModel(),
					modifier = modifier
				)
			}
		}
	}
}