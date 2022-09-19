package com.dynast.replycompose.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dynast.replycompose.data.BottomMenu
import com.dynast.replycompose.ui.theme.emphasisMediumAlpha

private val list = listOf(
    BottomMenu.Forward, BottomMenu.Reply, BottomMenu.ReplyAll, BottomMenu.Archive, BottomMenu.Delete
)

//private val list = listOf(
//    BottomMenu.Light, BottomMenu.Dark, BottomMenu.SystemDefault
//)

@Composable
fun MenuSheetWidget(
    onClick: (BottomMenu) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(vertical = 24.dp)) {
        items(items = list) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clickable { onClick(item) }
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (item.icon != null) {
                    Icon(
                        modifier = Modifier.padding(12.dp),
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(item.title),
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = emphasisMediumAlpha)
                    )
                }
                Text(
                    modifier = Modifier.then(
                        if (item.icon != null) {
                            Modifier
                        } else {
                            Modifier.padding(12.dp)
                        }
                    ),
                    text = stringResource(id = item.title),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = emphasisMediumAlpha)
                )
            }
        }
    }
}