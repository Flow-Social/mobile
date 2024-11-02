package me.floow.app.navigation

import kotlinx.serialization.Serializable
import me.floow.app.R

sealed interface NavigationRoute

@Serializable
data object AuthDestinationsCluster : NavigationRoute

@Serializable
data object MainDestinationsCluster : NavigationRoute

@Serializable
data object RegistrationScreen : NavigationRoute

@Serializable
data object CreateProfileScreen : NavigationRoute

@Serializable
data object LoginScreen : NavigationRoute

@Serializable
data object FeedScreen : NavigationRoute

@Serializable
data object ChatsScreen : NavigationRoute

@Serializable
data object ProfileScreen : NavigationRoute

@Serializable
data class EditProfileScreen(
    val name: String,
    val username: String,
    val description: String
) : NavigationRoute

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        route = FeedScreen,//NavigationItem.Main.Feed.route,
        titleId = R.string.feed_bottom_nav_label,
        drawableIconId = me.floow.uikit.R.drawable.feed_icon,
    ),
    BottomNavigationItem(
        route = ChatsScreen,
        titleId = R.string.chats_bottom_nav_label,
        drawableIconId = me.floow.uikit.R.drawable.chats_icon,
    ),
    BottomNavigationItem(
        route = ProfileScreen,
        titleId = R.string.profile_bottom_nav_label,
        drawableIconId = me.floow.uikit.R.drawable.profile_icon,
    ),
)

val mainBottomBarNavigationDestinations = listOf(
    FeedScreen,
    ChatsScreen,
    ProfileScreen
)