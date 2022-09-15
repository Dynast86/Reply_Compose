package com.dynast.replycompose.ui.email

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dynast.replycompose.ui.components.EmailFormWidget
import com.dynast.replycompose.ui.theme.ReplyComposeTheme

@Composable
fun EmailContent(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    EmailFormWidget {
        Column(modifier = modifier.padding(top = 16.dp)) {

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
        EmailContent {

        }
    }
}