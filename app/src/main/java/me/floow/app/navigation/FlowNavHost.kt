package me.floow.app.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import me.floow.chats.ui.ChatsRoute
import me.floow.domain.auth.AuthenticationManager
import me.floow.domain.auth.models.AuthState
import me.floow.explore.ui.ExploreRoute
import me.floow.feed.ui.FeedRoute
import me.floow.login.ui.LoginRoute
import me.floow.login.uilogic.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FlowNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = NavigationItem.Auth.route,
            startDestination = NavigationItem.Auth.Login.route
        ) {
            composable(NavigationItem.Auth.Registration.route) {
                Text(text = "Registration")
            }

            composable(NavigationItem.Auth.Login.route) {
                LoginRoute(
                    onGoToHome = {
                        navController.navigate(NavigationItem.Main.route) {
                            popUpTo(NavigationItem.Auth.route) { inclusive = false }
                        }
                    },
                    viewModel = koinViewModel(),
                    modifier
                )
            }
        }

        navigation(
            route = NavigationItem.Main.route,
            startDestination = NavigationItem.Main.Feed.route
        ) {
            composable(NavigationItem.Main.Feed.route) {
                FeedRoute(modifier)
            }

            composable(NavigationItem.Main.Explore.route) {
                ExploreRoute(modifier)
            }

            composable(NavigationItem.Main.Chats.route) {
                ChatsRoute(modifier)
            }
        }
    }
}