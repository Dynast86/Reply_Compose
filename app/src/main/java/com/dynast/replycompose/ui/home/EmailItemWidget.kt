package com.dynast.replycompose.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dynast.replycompose.R
import com.dynast.replycompose.data.email.Email
import com.dynast.replycompose.ui.theme.emphasisHighAlpha
import com.dynast.replycompose.ui.theme.profileImageSize

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmailItemWidget(
    item: Email,
    onLongClick: (Email) -> Unit,
    onClick: (Long) -> Unit
) {
    val haptic = LocalHapticFeedback.current
    val avatar = rememberAsyncImagePainter(model = item.sender.avatar)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 4.dp, vertical = 2.dp)
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .then(
                    if (item.isStarred) {
                        Modifier.background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(topStart = 24.dp))
                    } else {
                        Modifier.background(color = MaterialTheme.colorScheme.surface)
                    }
                )
                .combinedClickable(
                    onClick = {
                        onClick(item.id)
                    },
                    onLongClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onLongClick(item)
                    })
                .padding(vertical = 16.dp)
        ) {
            Column(modifier = Modifier.padding(top = 8.dp)) {
                Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 16.dp)
                    ) {
                        Text(
                            text = item.senderPreview,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = emphasisHighAlpha)
                        )
                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = item.subject,
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    Image(
                        modifier = Modifier
                            .size(profileImageSize)
                            .clip(CircleShape),
                        painter = avatar,
                        contentDescription = stringResource(id = R.string.email_sender_profile_content_desc),
                        contentScale = ContentScale.Crop
                    )
                }
                if (item.hasBody) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                        text = item.body,
                        maxLines = 2,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = emphasisHighAlpha)
                    )
                }
                if (item.hasAttachments) {
                    AttachmentWidget(
                        modifier = Modifier
                            .height(96.dp)
                            .padding(top = 16.dp)
                    )
                }
            }
        }
    }
}