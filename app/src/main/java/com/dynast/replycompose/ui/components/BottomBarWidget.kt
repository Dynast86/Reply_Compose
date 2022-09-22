package com.dynast.replycompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomBarWidget(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    mainState: MainState,
    rotation: Float = 0f
) {
    val scope = rememberCoroutineScope()
    val alphaState = remember {
        derivedStateOf { if (mainState.sandwichBottomSheetState.currentValue == ModalBottomSheetValue.Hidden) 1f else 0f }
    }

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
                    scope.launch {
                        with(mainState) {
                            fabState.targetState = if (sandwichBottomSheetState.currentValue == ModalBottomSheetValue.Hidden) {
                                sandwichBottomSheetState.show()
                                false
                            } else {
                                sandwichBottomSheetState.hide()
                                true
                            }
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
                    .alpha(alphaState.value),
                text = stringResource(id = R.string.navigation_inbox),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        if (mainState.sandwichBottomSheetState.currentValue != ModalBottomSheetValue.Hidden) {
            IconButton(onClick = {
                with(mainState) {
                    scope.launch {
                        sandwichBottomSheetState.hide()
                        bottomMenus.value = settingsList
                        bottomSheetState.show()
                        fabState.targetState = sandwichBottomSheetState.currentValue == ModalBottomSheetValue.Hidden
                    }
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
