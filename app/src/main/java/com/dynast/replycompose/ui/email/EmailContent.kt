package com.dynast.replycompose.ui.email

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dynast.replycompose.data.email.Email
import com.dynast.replycompose.data.email.EmailStore
import com.dynast.replycompose.ui.components.EmailFormWidget
import com.dynast.replycompose.ui.theme.ReplyComposeTheme
import com.dynast.replycompose.ui.theme.emphasisHighAlpha

@Composable
fun EmailContent(
    modifier: Modifier = Modifier,
    item: Email,
    onBackPressed: () -> Unit
) {
    val painter = rememberAsyncImagePainter(item.sender.avatar)

    EmailFormWidget(modifier = Modifier.fillMaxSize()) {
        Column(modifier = modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)) {
            EmailSubjectWidget(subject = item.subject) { onBackPressed() }
            EmailSenderWidget(
                sender = item.senderPreview,
                recipients = item.recipientsPreview,
                avatar = painter
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                text = item.body,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = emphasisHighAlpha)
            )
            if (item.hasAttachments) {
                EmailAttachmentWidget(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeightIn(min = 200.dp, max = 450.dp)
                        .padding(top = 16.dp),
                    list = item.attachments
                )
            }
        }
    }
}

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
fun EmailContentPreview() {
    ReplyComposeTheme {
        EmailStore.get(0)?.let {
            EmailContent(item = it) {

            }
        }
    }
}