package com.dynast.replycompose.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dynast.replycompose.data.email.EmailAttachment

@Composable
fun AttachmentWidget(
    modifier: Modifier,
    list: List<EmailAttachment>
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(items = list) { item ->
            Image(
                modifier = Modifier
                    .width(150.dp)
                    .padding(2.dp),
                painter = rememberAsyncImagePainter(model = item.resId),
                contentDescription = item.contentDesc,
                contentScale = ContentScale.FillWidth
            )
        }
    }
}