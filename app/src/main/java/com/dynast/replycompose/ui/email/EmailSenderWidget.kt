package com.dynast.replycompose.ui.email

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dynast.replycompose.R
import com.dynast.replycompose.ui.theme.ReplyComposeTheme
import com.dynast.replycompose.ui.theme.emphasisHighAlpha
import com.dynast.replycompose.ui.theme.emphasisMediumAlpha

@Composable
fun EmailSenderWidget(
    modifier: Modifier = Modifier,
    sender: String,
    recipients: String,
    avatar: Painter,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Text(
                text = sender,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = emphasisHighAlpha)
            )
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = "To $recipients",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = emphasisMediumAlpha)
            )
        }
        Image(
            modifier = Modifier
                .size(profileImageSize)
                .clip(CircleShape),
            painter = avatar,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}

private val profileImageSize = 42.dp

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Night",
    backgroundColor = 0xFF000000
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Day"
)
@Composable
fun EmailSenderWidgetPreview() {
    ReplyComposeTheme {
        EmailSenderWidget(
            sender = "Ali Connors -- 12m",
            recipients = "Jeff, Kim,",
            avatar = painterResource(id = R.drawable.dummy_1)
        )
    }
}