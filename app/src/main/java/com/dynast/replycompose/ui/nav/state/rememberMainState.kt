package com.dynast.replycompose.ui.nav.state

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dynast.replycompose.data.BottomMenu

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberMainState(
    navController: NavHostController = rememberNavController(),
    fabState: MutableTransitionState<Boolean> = remember { MutableTransitionState(initialState = true) },
    bottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
    bottomMenus: MutableState<List<BottomMenu>> = remember {
        mutableStateOf(listOf(BottomMenu.Forward, BottomMenu.Reply, BottomMenu.ReplyAll, BottomMenu.Archive, BottomMenu.Delete))
    },
    sandwichState: MutableTransitionState<SandwichState> = remember { MutableTransitionState(initialState = SandwichState.Closed) }
): MainState = remember {
    MainState(
        navController = navController,
        fabState = fabState,
        bottomSheetState = bottomSheetState,
        bottomMenus = bottomMenus,
        sandwichState = sandwichState
    )
}