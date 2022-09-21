package com.dynast.replycompose.ui.main

import androidx.compose.animation.*
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dynast.replycompose.R
import com.dynast.replycompose.data.BottomMenu
import com.dynast.replycompose.ui.components.BottomBarWidget
import com.dynast.replycompose.ui.components.BottomSheetContent
import com.dynast.replycompose.ui.components.TopBarWidget
import com.dynast.replycompose.ui.home.MenuSheetWidget
import com.dynast.replycompose.ui.nav.NavGraph
import com.dynast.replycompose.ui.nav.NavItem
import com.dynast.replycompose.ui.nav.navigation
import com.dynast.replycompose.ui.nav.state.SandwichState
import com.dynast.replycompose.ui.nav.state.rememberMainState
import com.dynast.replycompose.ui.theme.replyWhite50Alpha060
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

    val sandwichBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    var currentRotation by remember { mutableStateOf(180f) }
    val rotation = remember { Animatable(currentRotation) }

    LaunchedEffect(Unit) {
        snapshotFlow { mainState.sandwichState.targetState }.collect {
            rotation.animateTo(
                targetValue = currentRotation + 180f
            ) {
                currentRotation = value
            }
            with(sandwichBottomSheetState) {
                if (it == SandwichState.Open) show() else hide()
            }
        }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { navBackStackEntry }.collect {
            val route = it?.destination?.route
            fabState.targetState = when (route) {
                NavItem.Search.route, NavItem.Compose.route -> false
                else -> true
            }
        }
    }


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
                            ContentTransform(
                                targetContentEnter = expandHorizontally() + fadeIn(),
                                initialContentExit = shrinkHorizontally() + fadeOut()
                            ).using(
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
                BottomBarWidget(
                    navController = navController,
                    mainState = mainState,
                    sandwichState = mainState.sandwichState,
                    rotation = rotation.value
                )
            }
        }
    ) { paddingValues ->
        NavGraph(
            modifier = Modifier.padding(paddingValues),
            navController = navController, startDestination = NavItem.Home.route,
            onItemLongClick = {
                scope.launch {
                    with(mainState) {
                        bottomMenus.value = menuList
                        bottomSheetState.show()
                    }
                }
            }
        )
        ModalBottomSheetLayout(
            sheetState = sandwichBottomSheetState,
            sheetContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues)
                ) {
                    SandwichWidget(state = sandwichBottomSheetState.currentValue)
                }
            },
            sheetElevation = 8.dp,
            sheetBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
            sheetShape = MaterialTheme.shapes.large,
            scrimColor = replyWhite50Alpha060,
            content = {

            })
//        Surface(elevation = 6.dp, color = Color.LightGray) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(100.dp)
//                    .padding(paddingValues)
//            ) {
//                Text(text = "!!!!!!")
//            }
//        }
    }

    BottomSheetContent(state = mainState.bottomSheetState) {
        MenuSheetWidget(list = mainState.bottomMenus.value) {
            scope.launch { mainState.bottomSheetState.hide() }
        }
    }
}

private val menuList = listOf(BottomMenu.Forward, BottomMenu.Reply, BottomMenu.ReplyAll, BottomMenu.Archive, BottomMenu.Delete)