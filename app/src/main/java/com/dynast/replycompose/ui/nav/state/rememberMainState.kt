package com.dynast.replycompose.ui.nav.state

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberMainState(
    navController: NavHostController = rememberNavController(),
    fabState: MutableTransitionState<Boolean> = remember { MutableTransitionState(initialState = true) },
    bottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
): MainState = remember {
    MainState(
        navController = navController,
        fabState = fabState,
        bottomSheetState = bottomSheetState
    )
}