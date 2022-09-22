@file:OptIn(ExperimentalAnimationApi::class)

package com.dynast.replycompose.ui.main

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dynast.replycompose.R
import com.dynast.replycompose.data.compose.AccountStore
import com.dynast.replycompose.ui.nav.NavigationModel
import com.dynast.replycompose.ui.theme.LargeComponentCornerRadius
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SandwichWidget(
    state: ModalBottomSheetState,
    id: Int = NavigationModel.INBOX_ID
) {
    NavigationModel.setNavigationMenuItemChecked(id)

    val items = NavigationModel.navigationList.collectAsState()
    val account = AccountStore.userAccounts.collectAsState()

    val scope = rememberCoroutineScope()
    var viewState by remember { mutableStateOf(0) }

    val shapeModifier = if (state.currentValue == ModalBottomSheetValue.Expanded) {
        Modifier.background(color = MaterialTheme.colorScheme.primary)
    } else {
        if (viewState == 0) {
            Modifier
                .padding(top = 24.dp)
                .clip(
                    shape = RoundedCornerShape(
                        topStart = LargeComponentCornerRadius,
                        topEnd = LargeComponentCornerRadius
                    )
                )
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(top = 24.dp)
        } else {
            Modifier.background(color = MaterialTheme.colorScheme.primary)
        }
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .then(shapeModifier)
        ) {
            if (viewState == 0) {
                items(items = items.value) { item ->
                    SandwichItemWidget(item) {
                        scope.launch { state.hide() }
                    }
                }
            } else {
                items(items = account.value) { item ->
                    AccountItemWidget(item = item) { viewState = 0 }
                }
            }
        }

        AnimatedVisibility(
            visible = state.currentValue != ModalBottomSheetValue.Expanded,
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut()
        ) {
            Image(
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .clickable {
                        viewState = 1
                    },
                painter = rememberAsyncImagePainter(model = R.drawable.dummy_1),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }
}