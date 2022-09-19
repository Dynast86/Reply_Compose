package com.dynast.replycompose.ui.nav.state

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterialApi::class)
@Stable
class MainState(
    val navController: NavHostController,
    val fabState: MutableTransitionState<Boolean>,
    val bottomSheetState: ModalBottomSheetState
)