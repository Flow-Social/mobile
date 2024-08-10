package com.flowme.flow.navigation

import com.flowme.flow.R

enum class NavigationCluster {
    Main,
    Login
}

enum class Screen {
    Feed,
    Explore,
    Chats,
    Registration
}

sealed class NavigationItem(val route: String) {
    data object Main : NavigationItem(NavigationCluster.Main.name) {
        data object Feed : NavigationItem(Screen.Feed.name)

        data object Chats : NavigationItem(Screen.Chats.name)

        data object Explore : NavigationItem(Screen.Explore.name)
    }

    data object Login : NavigationItem(NavigationCluster.Login.name) {
        data object Registration : NavigationItem(Screen.Registration.name)
    }
}

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        route = NavigationItem.Main.Feed.route,
        titleId = R.string.feed_bottom_nav_label,
        drawableIconId = R.drawable.feed_icon,
    ),
    BottomNavigationItem(
        route = NavigationItem.Main.Chats.route,
        titleId = R.string.chats_bottom_nav_label,
        drawableIconId = R.drawable.chats_icon,
    ),
    BottomNavigationItem(
        route = NavigationItem.Main.Explore.route,
        titleId = R.string.explore_bottom_nav_label,
        drawableIconId = R.drawable.search_icon,
    ),
)

val mainBottomBarNavigationDestinations = listOf(
    NavigationItem.Main.Feed.route,
    NavigationItem.Main.Chats.route,
    NavigationItem.Main.Explore.route,
)