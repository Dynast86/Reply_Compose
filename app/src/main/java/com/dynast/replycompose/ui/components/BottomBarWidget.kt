package com.dynast.replycompose.ui.components

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dynast.replycompose.R
import com.dynast.replycompose.data.BottomMenu
import com.dynast.replycompose.ui.nav.NavItem
import com.dynast.replycompose.ui.nav.navigation
import com.dynast.replycompose.ui.nav.state.MainState
import com.dynast.replycompose.ui.nav.state.SandwichState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomBarWidget(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    mainState: MainState,
    sandwichState: MutableTransitionState<SandwichState>,
    rotation: Float = 0f
) {
    val scope = rememberCoroutineScope()

    BottomAppBar(
        cutoutShape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
        backgroundColor = MaterialTheme.colorScheme.primary
    ) {
        Row(
            modifier = modifier
                .height(48.dp)
                .padding(start = 16.dp, top = 4.dp, bottom = 4.dp)
                .clip(CircleShape)
                .clickable {
                    with(sandwichState) {
                        targetState = if (targetState == SandwichState.Open) {
                            SandwichState.Closed
                        } else {
                            SandwichState.Open
                        }
                    }
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.rotate(rotation),
                painter = painterResource(id = R.drawable.ic_arrow_drop_up),
                contentDescription = stringResource(id = R.string.bottom_app_bar_chevron_content_desc),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
            )
            Image(
                modifier = Modifier
                    .size(32.dp)
                    .padding(start = 8.dp),
                painter = painterResource(id = R.drawable.ic_reply_logo),
                contentDescription = stringResource(id = R.string.bottom_app_bar_logo_content_desc),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .then(
                        Modifier.alpha(
                            if (sandwichState.targetState == SandwichState.Closed) 1f else 0f
                        )
                    ),
                text = stringResource(id = R.string.navigation_inbox),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        if (sandwichState.targetState == SandwichState.Open) {
            IconButton(onClick = {
                sandwichState.targetState = SandwichState.Closed
                scope.launch {
                    mainState.bottomMenus.value = settingsList
                    mainState.bottomSheetState.show()
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = stringResource(id = R.string.menu_item_settings),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        } else {
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

private val settingsList = listOf(BottomMenu.Light, BottomMenu.Dark, BottomMenu.SystemDefault)
