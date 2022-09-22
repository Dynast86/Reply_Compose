package com.dynast.replycompose.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dynast.replycompose.data.compose.Account
import com.dynast.replycompose.ui.theme.emphasisMediumAlpha
import com.dynast.replycompose.ui.theme.listItemHeight

@Composable
fun AccountItemWidget(
    item: Account,
    onClick: (Account) -> Unit
) {
    val color = if (item.isCurrentAccount) {
        MaterialTheme.colorScheme.secondary
    } else {
        MaterialTheme.colorScheme.onPrimary
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = listItemHeight)
            .clickable { onClick(item) }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape),
            painter = painterResource(id = item.avatar), contentDescription = item.email,
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = item.email,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            color = color.copy(alpha = emphasisMediumAlpha),
            style = MaterialTheme.typography.bodyLarge
        )
        if (item.checkedIcon != 0) {
            Image(
                painter = painterResource(id = item.checkedIcon),
                contentDescription = "Checked",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary.copy(alpha = emphasisMediumAlpha))
            )
        }
    }
}