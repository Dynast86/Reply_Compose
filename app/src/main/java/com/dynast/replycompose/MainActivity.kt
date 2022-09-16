package com.dynast.replycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dynast.replycompose.ui.components.TopBarWidget
import com.dynast.replycompose.ui.nav.NavGraph
import com.dynast.replycompose.ui.nav.NavItem
import com.dynast.replycompose.ui.nav.navigation
import com.dynast.replycompose.ui.theme.ReplyComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReplyComposeTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                var fabState by remember { mutableStateOf(true) }

                // A surface container using the 'background' color from the theme
                ReplyComposeTheme {
                    Scaffold(
                        floatingActionButton = {
                            if (fabState) {
                                FloatingActionButton(
                                    onClick = {
                                        navController.navigation(NavItem.Compose)
                                        fabState = false
                                    },
                                    backgroundColor = MaterialTheme.colorScheme.secondary,
                                    contentColor = MaterialTheme.colorScheme.secondaryContainer
                                ) {
                                    Icon(painter = painterResource(id = R.drawable.ic_edit), contentDescription = "Edit")
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
                                    Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f), thickness = 1.dp)
                                }
                            }
                        },
                        bottomBar = {
                            BottomAppBar(
                                cutoutShape = MaterialTheme.shapes.small.copy(
                                    CornerSize(percent = 50)
                                ),
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
                            }
                        }
                    ) { paddingValues ->
                        NavGraph(
                            modifier = Modifier.padding(paddingValues),
                            navController = navController, startDestination = NavItem.Home.route
                        )
                    }
                }
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    NavGraph(navController = navController, startDestination = NavItem.Home.route)
//                }
            }
        }
    }
}