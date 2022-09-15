package com.dynast.replycompose.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dynast.replycompose.ui.compose.ComposeContent
import com.dynast.replycompose.ui.email.EmailContent
import com.dynast.replycompose.ui.home.HomeContent
import com.dynast.replycompose.ui.search.SearchContent

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        composable(NavItem.Home.route) {
            HomeContent { item -> navController.navigation(item) }
        }
        composable(NavItem.Search.route) {
            SearchContent { navController.navigation(NavItem.Home) }
        }
        composable(NavItem.Compose.route) {
            ComposeContent { navController.navigation(NavItem.Home) }
        }
        composable(NavItem.Email.route) {
            EmailContent()
        }
    }
}

fun NavController.navigation(item: NavItem) {
    navigate(item.route) {
        graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) { saveState = true }
        }
        launchSingleTop = true
        restoreState = true
    }
}
