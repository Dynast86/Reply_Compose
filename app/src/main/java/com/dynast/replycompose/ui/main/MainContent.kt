package com.dynast.replycompose.ui.main

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dynast.replycompose.R
import com.dynast.replycompose.ui.components.BottomSheetContent
import com.dynast.replycompose.ui.components.TopBarWidget
import com.dynast.replycompose.ui.home.MenuSheetWidget
import com.dynast.replycompose.ui.nav.NavGraph
import com.dynast.replycompose.ui.nav.NavItem
import com.dynast.replycompose.ui.nav.navigation
import com.dynast.replycompose.ui.nav.state.rememberMainState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun MainContent() {
    val mainState = rememberMainState()
    val navController = mainState.navController
    val fabState = mainState.fabState
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val scope = rememberCoroutineScope()

    fabState.SetState(currentRoute)

    Scaffold(
        floatingActionButton = {
            AnimatedVisibility(
                visible = fabState.targetState,
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {
                FloatingActionButton(
                    onClick = { navController.navigation(NavItem.Compose) },
                    backgroundColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    AnimatedContent(
                        targetState = currentRoute,
                        transitionSpec = {
                            if (targetState == NavItem.Email.route) {
                                expandHorizontally() + fadeIn() with shrinkHorizontally() + fadeOut()
                            } else {
                                expandHorizontally() + fadeIn() with shrinkHorizontally() + fadeOut()
                            }.using(
                                SizeTransform(clip = false)
                            )
                        }
                    ) { route ->
                        Icon(
                            painter = painterResource(
                                id = if (route == NavItem.Email.route) {
                                    R.drawable.ic_reply_all
                                } else {
                                    R.drawable.ic_edit
                                }
                            ),
                            contentDescription = "Edit"
                        )
                    }

                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        topBar = {
            if (currentRoute == NavItem.Search.route) {
                Column {
                    TopBarWidget { item ->
                        when (item) {
                            NavItem.Close -> navController.navigation(NavItem.Home)
                            else -> Unit
                        }
                    }
                    MenuDefaults.Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f), thickness = 1.dp)
                }
            }
        },
        bottomBar = {
            AnimatedVisibility(
                visible = currentRoute == NavItem.Home.route || currentRoute == NavItem.Email.route,
                enter = expandVertically(animationSpec = spring(stiffness = 300f)),
                exit = shrinkVertically(animationSpec = spring(stiffness = 300f))
            ) {
                BottomAppBar(
                    cutoutShape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onSurface
                ) {
                    Row(
                        modifier = Modifier
                            .height(48.dp)
                            .padding(start = 16.dp, top = 4.dp, bottom = 4.dp)
                            .clip(CircleShape)
                            .clickable { },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_drop_up),
                            contentDescription = stringResource(id = R.string.bottom_app_bar_chevron_content_desc),
                        )
                        Image(
                            modifier = Modifier
                                .size(32.dp)
                                .padding(start = 8.dp),
                            painter = painterResource(id = R.drawable.ic_reply_logo),
                            contentDescription = stringResource(id = R.string.bottom_app_bar_logo_content_desc),
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            text = stringResource(id = R.string.navigation_inbox),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        navController.navigation(NavItem.Search)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = stringResource(id = R.string.menu_item_search),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavGraph(
            modifier = Modifier.padding(paddingValues),
            navController = navController, startDestination = NavItem.Home.route,
            onItemLongClick = {
                scope.launch {
                    mainState.bottomSheetState.show()
                }
            }
        )
    }

    BottomSheetContent(state = mainState.bottomSheetState) {
        MenuSheetWidget {

        }
    }
}


@Composable
fun MutableTransitionState<Boolean>.SetState(route: String?) {
    targetState = when (route) {
        NavItem.Search.route, NavItem.Compose.route -> false
        else -> true
    }
}