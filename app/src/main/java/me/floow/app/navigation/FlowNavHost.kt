package me.floow.app.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import me.floow.chats.ChatRoute
import me.floow.chats.ChatRouteInitialData
import me.floow.chatssearch.ui.SearchUsersRoute
import me.floow.chats.ChatsRoute
import me.floow.feed.ui.FeedRoute
import me.floow.login.ui.createprofile.CreateProfileRoute
import me.floow.login.ui.login.LoginRoute
import me.floow.profile.ui.edit.EditProfileRoute
import me.floow.profile.ui.edit.EditProfileRouteInitialData
import me.floow.profile.ui.profile.ProfileRoute
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

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
				FeedRoute(
					onPostCreateClick = {
						showNotImplementedToast(context)
					},
					modifier
				)
			}

			composable<EditProfileScreen> {
				val backStackEntry = navController.currentBackStackEntry
				val editProfileScreen: EditProfileScreen? =
					backStackEntry?.toRoute<EditProfileScreen>()

				EditProfileRoute(
					initialData = EditProfileRouteInitialData(
						name = editProfileScreen?.name ?: "",
						username = editProfileScreen?.username ?: "",
						description = editProfileScreen?.description ?: "",
						avatarUrl = editProfileScreen?.avatarUrl ?: "",
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

			composable<ProfileScreen>(
				deepLinks = listOf(
					navDeepLink<ProfileScreen>(
						basePath = profileDeeplinkUri
					)
				)
			) {
				val username = navController.currentBackStackEntry
					?.toRoute<ProfileScreen>()?.username

				Box(Modifier.fillMaxSize()) {
					Text(text = username ?: "no username, invalid input")
				}
			}

			composable<SelfProfileScreen> {
				ProfileRoute(
					goToProfileEditScreen = { name, username, description, avatarUrl ->
						navController.navigate(
							EditProfileScreen(
								name = name,
								username = username,
								description = description,
								avatarUrl = avatarUrl
							)
						)
					},
					goToAddPostScreen = {
						showNotImplementedToast(context)
					},
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
					onChatClick = { chatToNavigate ->
						navController.navigate(
							ChatScreen(
								interlocutorId = chatToNavigate.id,
								interlocutorName = chatToNavigate.name.value,
								interlocutorAvatarUri = chatToNavigate.avatarUrl.toString()
							)
						)
					},
					onSearchClick = {
						navController.navigate(SearchUsersScreen)
					},
					vm = koinInject(),
					modifier = modifier
				)
			}

			composable<ChatScreen> {
				val backStackEntry = navController.currentBackStackEntry
				val chatScreen: ChatScreen? =
					backStackEntry?.toRoute<ChatScreen>()

				ChatRoute(
					initialData = ChatRouteInitialData(
						chatInterlocutorId = chatScreen?.interlocutorId ?: -1L,
						chatInterlocutorName = chatScreen?.interlocutorName ?: "",
						chatInterlocutorAvatarUrl = chatScreen?.interlocutorAvatarUri?.let { Uri.parse(it) }
					),
					vm = koinViewModel(),
					modifier = modifier
				)
			}

			composable<SearchUsersScreen> {
				SearchUsersRoute(
					onBackClick = {
						navController.popBackStack()
					},
					onUserPick = {
						showNotImplementedToast(context)
					},
					vm = koinViewModel(),
					modifier = modifier
				)
			}
		}
	}
}

private fun showNotImplementedToast(context: Context) {
	Toast.makeText(context, "Фича ещё разрабатывается…", Toast.LENGTH_SHORT).show()
}