package me.floow.app.navigation

import me.floow.app.R

enum class NavigationCluster {
    Main,
    Auth
}

enum class Screen {
    Feed,
    Explore,
    Chats,
    Registration,
    Login,
}

sealed class NavigationItem(val route: String) {
    data object Main : NavigationItem(NavigationCluster.Main.name) {
        data object Feed : NavigationItem(Screen.Feed.name)

        data object Chats : NavigationItem(Screen.Chats.name)

        data object Explore : NavigationItem(Screen.Explore.name)
    }

    data object Auth : NavigationItem(NavigationCluster.Auth.name) {
        data object Registration : NavigationItem(Screen.Registration.name)

        data object Login : NavigationItem(Screen.Login.name)
    }
}

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        route = NavigationItem.Main.Feed.route,
        titleId = R.string.feed_bottom_nav_label,
        drawableIconId = me.floow.uikit.R.drawable.feed_icon,
    ),
    BottomNavigationItem(
        route = NavigationItem.Main.Chats.route,
        titleId = R.string.chats_bottom_nav_label,
        drawableIconId = me.floow.uikit.R.drawable.chats_icon,
    ),
    BottomNavigationItem(
        route = NavigationItem.Main.Explore.route,
        titleId = R.string.explore_bottom_nav_label,
        drawableIconId = me.floow.uikit.R.drawable.search_icon,
    ),
)

val mainBottomBarNavigationDestinations = listOf(
    NavigationItem.Main.Feed.route,
    NavigationItem.Main.Chats.route,
    NavigationItem.Main.Explore.route,
)