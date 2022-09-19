package com.dynast.replycompose.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.dynast.replycompose.data.email.Email
import com.dynast.replycompose.data.email.EmailStore
import com.dynast.replycompose.data.email.Mailbox
import com.dynast.replycompose.ui.nav.NavItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeContent(
    mailbox: Mailbox,
    onLongClick: (Email) -> Unit,
    onClick: (NavItem, Long) -> Unit
) {
    val items = EmailStore.getEmails(mailbox).collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            itemsIndexed(items.value) { _: Int, item: Email ->
                val dismissState = rememberDismissState()
                if (dismissState.isDismissed(DismissDirection.StartToEnd)) {
                    println("starred")
                }

//                SwipeToDismiss(
//                    state = dismissState,
//                    modifier = Modifier.padding(vertical = Dp(1f)),
//                    directions = setOf(DismissDirection.StartToEnd),
//                    dismissThresholds = { direction -> FractionalThreshold(if (direction == DismissDirection.StartToEnd) 0.2f else 0.05f) },
//                    background = {
//                        val color by animateColorAsState(
//                            when (dismissState.targetValue) {
//                                DismissValue.Default -> Color.White
//                                else -> Color.Red
//                            }
//                        )
//
//                        Box(
//                            Modifier
//                                .padding(horizontal = 24.dp)
//                                .clip(shape = RoundedCornerShape(16.dp))
//                                .fillMaxSize()
//                                .background(color),
//                            contentAlignment = Alignment.CenterStart
//                        ) {
//
//                        }
//                    }
//                ) {

                EmailItemWidget(
                    item = item,
                    onLongClick = { onLongClick(it) },
                    onClick = { id -> onClick(NavItem.Email, id) }
                )

//                }

            }
        }
    }
}


