package com.dynast.replycompose.ui.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dynast.replycompose.R
import com.dynast.replycompose.ui.nav.NavItem
import com.dynast.replycompose.ui.theme.ReplyComposeTheme
import com.dynast.replycompose.ui.theme.emphasisDisabledAlpha

@Composable
fun SubjectWidget(
    modifier: Modifier = Modifier,
    onClick: (NavItem) -> Unit
) {
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    var textState by remember { mutableStateOf(TextFieldValue()) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .padding(horizontal = 8.dp)
    ) {
        IconButton(onClick = { onClick(NavItem.Close) }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = stringResource(id = R.string.compose_close_content_desc),
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = emphasisDisabledAlpha)
            )
        }
        BasicTextField(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .focusRequester(focusRequester = focusRequester),
            value = textState,
            onValueChange = {
                textState = it
            },
            cursorBrush = SolidColor(MaterialTheme.colorScheme.tertiary),
            decorationBox = { innerTextField ->
                if (textState.text.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.compose_subject_hint),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                innerTextField()
            },
            textStyle = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
        )
        IconButton(onClick = {

        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_twotone_send),
                contentDescription = stringResource(id = R.string.compose_send_content_desc),
                tint = MaterialTheme.colorScheme.primary
            )
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
fun SubjectWidgetPreview() {
    ReplyComposeTheme {
        SubjectWidget {

        }
    }
}