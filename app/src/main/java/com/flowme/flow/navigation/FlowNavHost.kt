package com.flowme.flow.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.flowme.chats.ui.ChatsScreen
import com.flowme.explore.ui.ExploreScreen
import com.flowme.feed.ui.FeedScreen

@Composable
fun FlowNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController, startDestination = NavigationItem.Main.route) {
        navigation(
            route = NavigationItem.Main.route,
            startDestination = NavigationItem.Main.Feed.route
        ) {
            composable(NavigationItem.Main.Feed.route) {
                FeedScreen(modifier)
            }

            composable(NavigationItem.Main.Explore.route) {
                ExploreScreen(modifier)
            }

            composable(NavigationItem.Main.Chats.route) {
                ChatsScreen(modifier)
            }
        }

        navigation(
            route = NavigationItem.Login.route,
            startDestination = NavigationItem.Login.Registration.route
        ) {
            composable(NavigationItem.Login.Registration.route) {
                Text(text = "Registration")
            }
        }
    }
}