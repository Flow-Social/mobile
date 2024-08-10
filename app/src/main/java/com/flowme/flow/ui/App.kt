package com.flowme.flow.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.flowme.flow.navigation.BottomNavigationItem
import com.flowme.flow.navigation.FlowNavHost
import com.flowme.flow.navigation.mainBottomBarNavigationDestinations
import com.flowme.flow.navigation.bottomNavigationItems

@Composable
fun App(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            if (currentDestination?.route in mainBottomBarNavigationDestinations) {
                BottomBar(
                    currentRoute = navController.currentDestination?.route ?: "",
                    navigationItems = bottomNavigationItems,
                    onClick = { navController.navigate(it) }
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        FlowNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text("flow типа") },
        modifier = modifier
    )
}

@Composable
private fun BottomBar(
    currentRoute: String,
    navigationItems: List<BottomNavigationItem>,
    onClick: (route: String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier) {
        navigationItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { onClick(item.route) },
                icon = {
                    Icon(painterResource(item.drawableIconId), null)
                },
                label = { Text(text = stringResource(item.titleId)) }
            )
        }
    }
}