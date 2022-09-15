package com.dynast.replycompose.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dynast.replycompose.temp.search.SearchSuggestion

@Composable
fun SuggestItemWidget(
    modifier: Modifier = Modifier,
    item: SearchSuggestion
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable {

            }
            .padding(horizontal = 24.dp, vertical = 16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.iconResId),
                contentDescription = "Icon",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = item.subtitle,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}