package com.dynast.replycompose.ui.nav

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dynast.replycompose.data.email.Email
import com.dynast.replycompose.data.email.EmailStore
import com.dynast.replycompose.data.email.Mailbox
import com.dynast.replycompose.ui.compose.ComposeContent
import com.dynast.replycompose.ui.email.EmailContent
import com.dynast.replycompose.ui.home.HomeContent
import com.dynast.replycompose.ui.nav.state.rememberMainState
import com.dynast.replycompose.ui.search.SearchContent

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    onItemLongClick: (Email) -> Unit
) {
    var emailId by remember { mutableStateOf(0L) }

    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        composable(NavItem.Home.route) {
            HomeContent(mailbox = Mailbox.INBOX, onLongClick = {
                onItemLongClick(it)
            }) { navItem: NavItem, id: Long ->
                emailId = id
                navController.navigation(navItem)
            }
        }
        composable(NavItem.Search.route) {
            SearchContent()
        }
        composable(NavItem.Compose.route) {
            ComposeContent { navController.navigation(NavItem.Home) }
        }
        composable(NavItem.Email.route) {
            val email = EmailStore.get(emailId)
            if (email != null) {
                EmailContent(item = email) { navController.navigation(NavItem.Home) }
            } else {
                // Error !!!
            }
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