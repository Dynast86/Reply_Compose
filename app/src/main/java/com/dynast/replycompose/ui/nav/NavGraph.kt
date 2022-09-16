package com.dynast.replycompose.ui.nav

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dynast.replycompose.data.email.allEmails
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
            SearchContent()
        }
        composable(NavItem.Compose.route) {
            ComposeContent { navController.navigation(NavItem.Home) }
            setBottomAppBarForCompose()
        }
        composable(NavItem.Email.route) {
            val emailId = 0L
            val email = allEmails.find { it.id == emailId }
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

fun setBottomAppBarForCompose() {
    hideBottomAppBar()
}

private fun hideBottomAppBar() {
//    binding.run {
//        bottomAppBar.performHide()
//        // Get a handle on the animator that hides the bottom app bar so we can wait to hide
//        // the fab and bottom app bar until after it's exit animation finishes.
//        bottomAppBar.animate().setListener(object : AnimatorListenerAdapter() {
//            var isCanceled = false
//            override fun onAnimationEnd(animation: Animator?) {
//                if (isCanceled) return
//
//                // Hide the BottomAppBar to avoid it showing above the keyboard
//                // when composing a new email.
//                bottomAppBar.visibility = View.GONE
//                fab.visibility = View.INVISIBLE
//            }
//            override fun onAnimationCancel(animation: Animator?) {
//                isCanceled = true
//            }
//        })
//    }
}