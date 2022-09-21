@file:OptIn(ExperimentalAnimationApi::class)

package com.dynast.replycompose.ui.main

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dynast.replycompose.R
import com.dynast.replycompose.ui.nav.NavigationModel
import com.dynast.replycompose.ui.theme.LargeComponentCornerRadius


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SandwichWidget(
    state: ModalBottomSheetValue,
    id: Int = NavigationModel.INBOX_ID
) {
    NavigationModel.setNavigationMenuItemChecked(id)
    val items = NavigationModel.navigationList.collectAsState()

    println("state : $state")

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .clip(
                    shape = RoundedCornerShape(
                        topStart = LargeComponentCornerRadius,
                        topEnd = LargeComponentCornerRadius
                    )
                )
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(top = 24.dp)
        ) {
            items(items = items.value) { item ->
                SandwichItemWidget(item)
            }
        }

        AnimatedVisibility(
            visible = state != ModalBottomSheetValue.Expanded,
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut()
        ) {
            Image(
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.dummy_1),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }
}