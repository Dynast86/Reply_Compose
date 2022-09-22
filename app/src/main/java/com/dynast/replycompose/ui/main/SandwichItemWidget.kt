package com.dynast.replycompose.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dynast.replycompose.R
import com.dynast.replycompose.ui.nav.NavigationModelItem
import com.dynast.replycompose.ui.theme.emphasisMediumAlpha
import com.dynast.replycompose.ui.theme.listItemHeight

@Composable
fun SandwichItemWidget(
    item: NavigationModelItem,
    onClick: () -> Unit
) {
    when (item) {
        is NavigationModelItem.NavEmailFolder -> {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(listItemHeight)
                .clickable { onClick() }
                .padding(horizontal = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1.0f)
                        .padding(end = 32.dp),
                    painter = painterResource(id = R.drawable.ic_twotone_folder),
                    contentDescription = item.emailFolder,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary.copy(alpha = emphasisMediumAlpha)),
                    contentScale = ContentScale.None
                )
                Text(
                    text = item.emailFolder,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = emphasisMediumAlpha),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        is NavigationModelItem.NavDivider -> {
            Column(modifier = Modifier.padding(bottom = 16.dp, start = 32.dp, end = 32.dp)) {
                Divider(
                    modifier = Modifier
                        .width(width = 200.dp)
                        .padding(vertical = 32.dp),
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.1f)
                )
                Text(
                    text = item.title.uppercase(),
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = emphasisMediumAlpha),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
        is NavigationModelItem.NavMenuItem -> {
            val color = if (item.checked) {
                MaterialTheme.colorScheme.secondary
            } else {
                MaterialTheme.colorScheme.onPrimary
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(listItemHeight)
                .clickable { onClick() }
                .padding(horizontal = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1.0f)
                        .padding(end = 32.dp),
                    painter = painterResource(id = item.icon),
                    contentDescription = stringResource(id = item.titleRes),
                    colorFilter = ColorFilter.tint(color.copy(alpha = emphasisMediumAlpha)),
                    contentScale = ContentScale.None
                )
                Text(
                    text = stringResource(id = item.titleRes),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = color.copy(alpha = emphasisMediumAlpha),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}