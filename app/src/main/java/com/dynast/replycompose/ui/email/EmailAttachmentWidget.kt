package com.dynast.replycompose.ui.email

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dynast.replycompose.data.email.EmailAttachment

@Composable
fun EmailAttachmentWidget(
    modifier: Modifier = Modifier,
    list: List<EmailAttachment>
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3)
    ) {
        items(items = list) { item ->
            Image(
                painter = rememberAsyncImagePainter(model = item.resId),
                contentDescription = item.contentDesc,
                contentScale = ContentScale.FillHeight
            )
        }
    }
//    LazyRow(
//        modifier = modifier.fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically,
//        contentPadding = PaddingValues(horizontal = 12.dp)
//    ) {
//        items(items = list) { item ->
//            Image(
//                modifier = Modifier
//                    .height(200.dp)
//                    .padding(2.dp),
//                painter = rememberAsyncImagePainter(model = item.resId),
//                contentDescription = item.contentDesc,
//                contentScale = ContentScale.FillHeight
//            )
//        }
//    }
}