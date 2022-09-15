package com.dynast.replycompose.ui.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dynast.replycompose.R
import com.dynast.replycompose.ui.theme.ReplyComposeTheme

@Composable
fun MessageFieldWidget(
    modifier: Modifier = Modifier
) {
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    var textState by remember { mutableStateOf(TextFieldValue()) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 250.dp)
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
    ) {
        BasicTextField(
            modifier = Modifier
                .matchParentSize()
                .padding(bottom = 32.dp)
                .focusRequester(focusRequester = focusRequester),
            value = textState,
            onValueChange = {
                textState = it
            },
            cursorBrush = SolidColor(MaterialTheme.colorScheme.tertiary),
            decorationBox = { innerTextField ->
                if (textState.text.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.compose_body_hint),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                innerTextField()
            },
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 24.sp
            ),
        )
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
fun MessageFieldWidgetPreview() {
    ReplyComposeTheme {
        MessageFieldWidget()
    }
}