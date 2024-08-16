package com.flowme.flow.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navOptions
import com.flowme.chats.ui.ChatsRoute
import com.flowme.explore.ui.ExploreRoute
import com.flowme.feed.ui.FeedRoute
import com.flowme.login.ui.LoginRoute

@Composable
fun FlowNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController, startDestination = NavigationItem.Auth.route) {
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