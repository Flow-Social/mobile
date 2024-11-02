package me.floow.app.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import me.floow.app.navigation.*

@Composable
fun App(
	startDestination: NavigationRoute,
	modifier: Modifier = Modifier
) {
	val navController = rememberNavController()
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentDestination = navBackStackEntry?.destination

	Scaffold(
		bottomBar = {
			val bottomBarVisible = currentDestination?.hierarchy?.any { hierarchyItem ->
				mainBottomBarNavigationDestinations.any { mainDestination ->
					hierarchyItem.hasRoute(mainDestination::class)
				}
			} == true

			if (bottomBarVisible) {
				BottomBar(
					currentDestination = navController.currentDestination,
					navigationItems = bottomNavigationItems,
					onClick = {
						navController.navigate(it) {
							launchSingleTop = true
						}
					}
				)
			}
		},
		modifier = modifier
	) { innerPadding ->
		FlowNavHost(
			navController = navController,
			startDestination = startDestination,
			modifier = Modifier
				.fillMaxSize()
				.padding(innerPadding)
		)
	}
}

@Composable
private fun BottomBar(
	currentDestination: NavDestination?,
	navigationItems: List<BottomNavigationItem>,
	onClick: (route: NavigationRoute) -> Unit,
	modifier: Modifier = Modifier
) {
	NavigationBar(modifier) {
		navigationItems.forEach { item ->
			val selected = currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } ?: false

			NavigationBarItem(
				selected = selected,
				onClick = {
					if (!selected) onClick(item.route)
				},
				icon = {
					Icon(painterResource(item.drawableIconId), null)
				},
				label = { Text(text = stringResource(item.titleId)) }
			)
		}
	}
}